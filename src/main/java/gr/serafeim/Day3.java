package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day3 {
    static ArrayList<ArrayList<Integer>> readInput() throws IOException {
        //File file = new File("c:\\progr\\java\\aoc2016-java\\day2.txt");
        final File file = new File("day3.txt");

        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l;
        final ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        while( (l= br.readLine())!=null){
            final ArrayList<Integer> ll = new ArrayList<Integer>();
            for(String s: l.split(" ")) {
                if(s.isBlank()) continue;
                ll.add(Integer.parseInt(s));
            }
            res.add(ll);
        }
        return res;
    }

    public static void part1() {
        int possible = 0;
        try {
            for (ArrayList<Integer> triangle : readInput()) {
                int max = Collections.max(triangle);
                triangle.remove(triangle.indexOf(max));
                if(max < triangle.get(0) + triangle.get(1)) {
                    possible++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(possible);
    }

    static ArrayList<ArrayList<Integer>> readInput2() throws IOException {
        final File file = new File("day3.txt");

        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l;
        final ArrayList<Integer> res0 = new ArrayList<Integer>();
        while( (l= br.readLine())!=null){
            final ArrayList<Integer> ll = new ArrayList<Integer>();
            for(String s: l.split(" ")) {
                if(s.isBlank()) continue;
                res0.add(Integer.parseInt(s));
            }
        }

        System.out.println(res0);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        // Trickery to avoid complex numbering
        // It works this way:
        // a b c
        // d e f
        // g h i
        // j k l
        // m n p
        // We want to get [a d g], [b e h] etc
        // We have [a b c d e f g h i ...]
        // So first we remove elements 6-3-0 (i.e g d a) (notice that the big index is removed first to keep the remaining indeces unaffected)
        // Then we'll have [ b c e f h i ...] so now we'll remove 4-2-0 (ie h e b)
        // Finally we'' have [c f i ...] so we'll just remove the first three elements (i kept the same 2-1-0 schema)
        // Then the same trickery starts for the rest of the elements until the list is empty
        while(!res0.isEmpty()) {
            ArrayList<Integer> l1 = new ArrayList<>();
            l1.add(res0.remove(6));
            l1.add(res0.remove(3));
            l1.add(res0.remove(0));
            res.add(l1);

            l1 = new ArrayList<>();
            l1.add(res0.remove(4));
            l1.add(res0.remove(2));
            l1.add(res0.remove(0));
            res.add(l1);

            l1 = new ArrayList<>();
            l1.add(res0.remove(2));
            l1.add(res0.remove(1));
            l1.add(res0.remove(0));
            res.add(l1);
        }

        return res;
    }

    public static void part2() {
        int possible = 0;
        try {
            for (ArrayList<Integer> triangle : readInput2()) {
                int max = Collections.max(triangle);
                triangle.remove(triangle.indexOf(max));
                if(max < triangle.get(0) + triangle.get(1)) {
                    possible++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(possible);
    }
}
