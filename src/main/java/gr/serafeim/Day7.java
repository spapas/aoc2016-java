package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7 {
    static ArrayList<String> readInput() throws IOException {
        //File file = new File("c:\\progr\\java\\aoc2016-java\\day2.txt");
        final File file = new File("day7.txt");

        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l;
        final ArrayList<String> res = new ArrayList<String>();
        while( (l= br.readLine())!=null){
            res.add(l);
        }
        return res;
    }

    public static void part1() {
        
    }
       
}
