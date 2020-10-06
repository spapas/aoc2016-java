package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day8 {
    static final int width = 50;
    static final int height  = 6;

    /*
    static ArrayList<Address> readInput() throws IOException {
        //File file = new File("c:\\progr\\java\\aoc2016-java\\day2.txt");
        final File file = new File("day8.txt");

        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l;
        final ArrayList<Address> res = new ArrayList<Address>();
        while( (l= br.readLine())!=null){
            res.add(Address.getAddress(l));
        }
        return res;
    }*/

    public static void part1() {
        try {
            Boolean [][] matrix = new Boolean[width][height];
            int i,j;
            for(i=0;i<width;i++) {
                for (j=0;j<height;j++) matrix[i][j] = false;
            }
            //readInput();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

       
}
