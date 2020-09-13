package gr.serafeim;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gr.serafeim.Util.foldLeft;

public class Day1 {
    static final String input = "L1, L3, L5, L3, R1, L4, L5, R1, R3, L5, R1, L3, L2, L3, R2, R2, L3, L3, R1, L2, R1, L3, L2, R4, R2, L5, R4, L5, R4, L2, R3, L2, R4, R1, L5, L4, R1, L2, R3, R1, R2, L4, R1, L2, R3, L2, L3, R5, L192, R4, L5, R4, L1, R4, L4, R2, L5, R45, L2, L5, R4, R5, L3, R5, R77, R2, R5, L5, R1, R4, L4, L4, R2, L4, L1, R191, R1, L1, L2, L2, L4, L3, R1, L3, R1, R5, R3, L1, L4, L2, L3, L1, L1, R5, L4, R1, L3, R1, L2, R1, R4, R5, L4, L2, R4, R5, L1, L2, R3, L4, R2, R2, R3, L2, L3, L5, R3, R1, L4, L3, R4, R2, R2, R2, R1, L4, R4, R1, R2, R1, L2, L2, R4, L1, L2, R3, L3, L5, L4, R4, L3, L1, L5, L3, L5, R5, L5, L4, L2, R1, L2, L4, L2, L4, L1, R4, R4, R5, R1, L4, R2, L4, L2, L4, R2, L4, L1, L2, R1, R4, R3, R2, R2, R5, L1, L2";
    //static final String input = "R8, R4, R4, R8";

    public enum Facing {
        U,
        D,
        L,
        R;
    }

    public enum LR {
        L, R;
    }

    record Position(int x, int y, Facing facing) {
    }

    record Direction(LR lr, int steps) {
        public static Direction fromString(String s) {
            LR lr;
            int steps;

            if (s.charAt(0) == 'L') {
                lr = LR.L;
            } else {
                lr = LR.R;
            }
            steps = Integer.parseInt(s.substring(1));
            return new Direction(lr, steps);
        }
    }

    record Coords(int x, int y) {}

    static Position moveUp(Position p, int steps) {
        return new Position(p.x, p.y-steps, Facing.U);
    }
    static Position moveDown(Position p, int steps) {
        return new Position(p.x, p.y+steps, Facing.D);
    }
    static Position moveLeft(Position p, int steps) {
        return new Position(p.x-steps, p.y, Facing.L);
    }
    static Position moveRight(Position p, int steps) {
        return new Position(p.x+steps, p.y, Facing.R);
    }

    static Position movePosition(Position p, Direction d) {
        if(d.lr==LR.L) {
            if(p.facing==Facing.U) {
                return moveLeft(p, d.steps);
            } else if(p.facing==Facing.L) {
                return moveDown(p, d.steps);
            } else if(p.facing==Facing.D) {
                return moveRight(p, d.steps);
            } else if(p.facing==Facing.R) {
                return moveUp(p, d.steps);
            }
        } else {
            if(p.facing==Facing.U) {
                return moveRight(p, d.steps);
            } else if(p.facing==Facing.R) {
                return moveDown(p, d.steps);
            } else if(p.facing==Facing.D) {
                return moveLeft(p, d.steps);
            } else if(p.facing==Facing.L) {
                return moveUp(p, d.steps);
            }
        }
        return p;
    }


    public static void part1() {
        String [] parts = input.split(",");

        Position finalPos = foldLeft(Arrays.stream(parts).map(String::trim).map(Direction::fromString), new Position(0,0, Facing.U), Day1::movePosition);

        System.out.println(finalPos);
        System.out.println(Math.abs(finalPos.x) + Math.abs(finalPos.y));

    }

    public static List<Coords> getCoordsBetween(Coords prev, Coords next ) {
        ArrayList<Coords> coords = new ArrayList<>();
        if(prev.x!=next.x) {
            if(prev.x < next.x) {
                for(int i=next.x; i!=prev.x; i--) {
                    coords.add(new Coords(i, prev.y));
                }
            } else {
                for(int i=next.x; i!=prev.x; i++) {
                    coords.add(new Coords(i, prev.y));
                }
            }
        } else {
            if(prev.y < next.y) {
                for(int i=next.y; i!=prev.y; i--) {
                    coords.add(new Coords(prev.x, i));
                }
            } else {
                for(int i=next.y; i!=prev.y; i++) {
                    coords.add(new Coords(prev.x, i));
                }
            }
        }
        Collections.reverse(coords);
        return coords;
    }


    public static void part2() {
        String [] parts = input.split(",");
        //HashSet<Position> positions = new HashSet<>();
        HashSet<Coords> coords = new HashSet<>();
        Position previous = new Position(0, 0, Facing.U);
        //positions.add(previous);
        coords.add(new Coords(previous.x, previous.y));
        List<Direction> directions = Arrays.stream(parts).map(String::trim).map(Direction::fromString).collect(Collectors.toList());

        outerloop:
        for(Direction d: directions) {
            Position p =  movePosition(previous, d);
            Coords prevCoords = new Coords(previous.x, previous.y);
            Coords nextCoords = new Coords(p.x, p.y);
            List<Coords> between = getCoordsBetween(prevCoords, nextCoords);

            for (Coords b: between) {
                if(coords.contains(b)) {
                    System.out.println(b);
                    System.out.println(Math.abs(b.x) + Math.abs(b.y));
                    break outerloop;
                }
                coords.add(b);
            }

            previous = p;

        }


    }
}
