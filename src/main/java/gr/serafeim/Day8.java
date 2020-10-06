package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day8 {
    static final int width = 50;
    static final int height  = 6;
    static final String RECT = "rect ";
    static final String RRY = "rotate row y=";
    static final String RCX = "rotate column x=";

    static ArrayList<String> readInput() throws IOException {
        final File file = new File("day8.txt");

        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l;
        final ArrayList<String> res = new ArrayList<>();
        while( (l= br.readLine())!=null){
            res.add(l);
        }
        return res;
    }

    public static Boolean [][] part1() {
        Boolean [][] matrix = new Boolean[width][height];
        int i,j;
        for(i=0;i<width;i++) {
            for (j=0;j<height;j++) {
                matrix[i][j] = false;
            }
        }

        try {
            for(String s: readInput()) {
                if(s.startsWith(RECT)) {
                    String [] parts = s.substring(RECT.length()).split("x");
                    int rx = Integer.parseInt(parts[0]);
                    int ry = Integer.parseInt(parts[1]);
                    for(i=0;i<rx;i++) {
                        for (j=0;j<ry;j++) {
                            matrix[i][j] = true;
                        }
                    }
                } else if (s.startsWith(RRY)) {
                    String [] parts = s.substring(RRY.length()).split(" by ");
                    Boolean [] tmpRow = new Boolean[width];
                    int y = Integer.parseInt(parts[0]);
                    int by = Integer.parseInt(parts[1]);
                    for(i=0;i<width;i++) {
                        tmpRow[ (i+by)%width] = matrix[i][y];
                    }
                    for(i=0;i<width;i++) {
                        matrix[i][y] = tmpRow[i];
                    }

                } else  if (s.startsWith(RCX)) {
                    String [] parts = s.substring(RCX.length()).split(" by ");
                    Boolean [] tmpCol = new Boolean[height];
                    int x = Integer.parseInt(parts[0]);
                    int by = Integer.parseInt(parts[1]);
                    for(j=0;j<height;j++) {
                        tmpCol[ (j+by)%height] = matrix[x][j];
                    }
                    for(j=0;j<height;j++) {
                        matrix[x][j] = tmpCol[j];
                    }
                } else {
                    throw new Exception();

                }
            }
            int sum = 0;
            for(i=0;i<width;i++) {
                for (j=0;j<height;j++) {
                    if(matrix[i][j]) {
                        sum++;
                    }
                }
            }
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }

        return matrix;
    }

    static void part2() {
        Boolean[][] matrix = part1();
        int i,j;
        for(j=0;j<height;j++) {
            for (i=0;i<width;i++) {
                if(matrix[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
       
}
