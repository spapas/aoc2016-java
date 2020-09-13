package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Day2 {
    public enum UDLR {
        U,
        D,
        L,
        R;
    }


    static ArrayList<ArrayList<UDLR>> readInput() throws IOException {
        //File file = new File("c:\\progr\\java\\aoc2016-java\\day2.txt"); 
        final File file = new File("day2.txt"); 
        
        final BufferedReader br = new BufferedReader(new FileReader(file)); 
        String l;
        final ArrayList<ArrayList<UDLR>> res = new ArrayList<ArrayList<UDLR>>();
        while( (l= br.readLine())!=null){
            final ArrayList<UDLR> ll = new ArrayList<UDLR>();
            for(int i=0;i<l.length();i++) {
                final Character c = l.charAt(i);
                switch(c) {
                    case 'U': ll.add(UDLR.U); break;
                    case 'D': ll.add(UDLR.D); break;
                    case 'L': ll.add(UDLR.L); break;
                    case 'R': ll.add(UDLR.R); break;
                }
            }
            res.add(ll);
        }
        return res;
    }

    record Coords(final int x, final int y) {}

    public static Coords move(final Coords current, final UDLR direction) {
        int dx = 0;
        int dy = 0;

        switch (direction) {
            case U: if (current.y>0) dy--; break;
            case D: if (current.y<2) dy++; break;
            case L: if (current.x>0) dx--; break;
            case R: if (current.x<2) dx++; break;
                
        }

        return new Coords(current.x+dx, current.y+dy);
    }
    
    static int getNumberFromCoords(Coords c) {
        if (c.y==0) {
            if (c.x==0) return 1;
            if (c.x==1) return 2;
            if (c.x==2) return 3;
        }
        if (c.y==1) {
            if (c.x==0) return 4;
            if (c.x==1) return 5;
            if (c.x==2) return 6;
        }
        if (c.y==2) {
            if (c.x==0) return 7;
            if (c.x==1) return 8;
            if (c.x==2) return 9;
        }
        return -1;
    }

    static void part1() {
        try {
            final ArrayList<ArrayList<UDLR>> inp = readInput();
            //System.out.println(inp);
            Coords current = new Coords(1,1);
            for(ArrayList<UDLR> inputLine: inp) {
                for(UDLR direction: inputLine) {
                    current = move(current, direction);

                }
                System.out.println(getNumberFromCoords(current));
            }
            
        } catch(final Exception e) {
            e.printStackTrace();
        }

    }

    
    public static Coords move2(final Coords current, final UDLR direction) {
        int dx = 0;
        int dy = 0;

        switch (direction) {
            case U: 
                if (current.y == -2) dy=0;
                else if (current.y == -1 && (current.x==-1 || current.x==1)) dy=0;
                else if (current.y == 0 && (current.x==-2 || current.x==2)) dy=0;
                else dy--;
                break;
            case D: 
                if (current.y == 2) dy=0;
                else if (current.y == 1 && (current.x==-1 || current.x==1)) dy=0;
                else if (current.y == 0 && (current.x==-2 || current.x==2)) dy=0;
                else dy++;
                break;
            case L: 
                if (current.x == -2) dx=0;
                else if (current.x == -1 && (current.y==-1 || current.y==1)) dx=0;
                else if (current.x == 0 && (current.y==-2 || current.y==2)) dx=0;
                else dx--;
                break;
            case R: 
                if (current.x == 2) dx=0;
                else if (current.x == 1 && (current.y==-1 || current.y==1)) dx=0;
                else if (current.x == 0 && (current.y==-2 || current.y==2)) dx=0;
                else dx++;
                break;
        }

        return new Coords(current.x+dx, current.y+dy);
    }
    
    static char getNumberFromCoords2(Coords c) {
        if (c.y==0) {
            if (c.x==-2) return '5';
            if (c.x==-1) return '6';
            if (c.x==0) return '7';
            if (c.x==1) return '8';
            if (c.x==2) return '9';
        }
        if (c.y==1) {
            if (c.x==-1) return 'A';
            if (c.x==0) return '5';
            if (c.x==1) return '6';
        }
        if (c.y==2) {
            if (c.x==0) return 'D';
        }
        if (c.y==-1) {
            if (c.x==-1) return '2';
            if (c.x==0) return '3';
            if (c.x==1) return '4';
        }
        if (c.y==-2) {
            if (c.x==0) return '1';
        }
        return 'Z';
    }
    
    static void part2() {
        try {
            final ArrayList<ArrayList<UDLR>> inp = readInput();
            //System.out.println(inp);
            Coords current = new Coords(0,0);
            for(ArrayList<UDLR> inputLine: inp) {
                for(UDLR direction: inputLine) {
                    current = move2(current, direction);

                }
                System.out.println(getNumberFromCoords2(current));
            }
            
        } catch(final Exception e) {
            e.printStackTrace();
        }

    }
}
