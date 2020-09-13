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
}
