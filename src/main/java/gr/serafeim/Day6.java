package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day6 {
    static ArrayList<String> readInput() throws IOException {
        //File file = new File("c:\\progr\\java\\aoc2016-java\\day2.txt");
        final File file = new File("day6.txt");

        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l;
        final ArrayList<String> res = new ArrayList<String>();
        while( (l= br.readLine())!=null){
            res.add(l);
        }
        return res;
    }

    public static void part1() {
        int possible = 0;
        try {
            HashMap<Integer, Integer> [] positions = new HashMap[8];
            for(int i=0;i<8;i++) {
                positions[i] = new HashMap<>();
            }
            ArrayList<String> inp = readInput();
            for(String s: inp) {
                int[] ints = s.chars().toArray();
                for(int i=0;i<ints.length;i++) {
                    Integer cnt = positions[i].get(ints[i]);
                    if(cnt==null) {
                        cnt = 1;
                    } else {
                        cnt++;
                    }
                    positions[i].put(ints[i], cnt);
                }
            }
            for(int i=0;i<8;i++) {
                HashMap<Integer, Integer> position = positions[i];
                Optional<Map.Entry<Integer, Integer>> max = position.entrySet().stream().max(Comparator.comparingInt(x -> x.getValue()));
                System.out.print((char)max.get().getKey().intValue());
            }
        } catch(Exception e) {

        }
    }
}
