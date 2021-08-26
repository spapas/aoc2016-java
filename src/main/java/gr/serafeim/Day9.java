package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day9 {


    static char [] readInput() throws IOException {
        final File file = new File("day9.txt");

        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l= br.readLine();
        char [] res = new char[l.length()];
        l.getChars(0, l.length(), res, 0);

        return res;
    }

    public static void part1() {
        int l = 0;
        StringBuffer sb = new StringBuffer();
        int len = 0;
        int times = 0;
        boolean readingLen = false;
        boolean readingTimes = false;

        try {
            //char [] input = "X(8x2)(3x3)ABCY".toCharArray();
            char [] input = readInput();
            for(int i=0;i<input.length;i++) {
                System.out.println(i);
                char cc = input[i];
                System.out.println(cc);

                if(cc == '(') {
                    sb = new StringBuffer();
                    readingLen = true;

                } else if (cc == 'x') {
                    len = Integer.parseInt(sb.toString());
                    System.out.println("LEN IS " + len);
                    sb = new StringBuffer();
                    readingLen = false;
                    readingTimes = true;
                } else if (cc == ')') {
                    readingLen = false;
                    readingTimes = false;
                    times = Integer.parseInt(sb.toString());
                    System.out.println("TIMES IS " + times);
                    l+=times * len;
                    i+= len;
                } else {
                    if(readingLen) {
                        sb.append(cc);
                        continue;
                    }
                    if(readingTimes) {
                        sb.append(cc);
                        continue;
                    }
                    l++;
                }

            }
            System.out.println("OK " + l);
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }


    }

    public static long  part2Counter(char [] input) {
        long l = 0;
        StringBuffer sb = new StringBuffer();
        long len = 0;
        long times = 0;
        boolean readingLen = false;
        boolean readingTimes = false;
        System.out.println("COUNTER");
        System.out.println(readingLen);
        System.out.println(readingTimes);

        for(long  i=0;i<input.length;i++) {
            System.out.println(i);
            char cc = input[(int)i];
            System.out.println(cc);

            if(cc == '(') {
                sb = new StringBuffer();
                readingLen = true;

            } else if (cc == 'x') {
                len = Integer.parseInt(sb.toString());
                System.out.println("LEN IS " + len);
                sb = new StringBuffer();
                readingLen = false;
                readingTimes = true;
            } else if (cc == ')') {
                readingLen = false;
                readingTimes = false;
                times = Integer.parseInt(sb.toString());
                System.out.println("TIMES IS " + times);
                char [] c2 = new char[(int)len];
                for(long  j=0;j<c2.length;j++) {
                    c2[(int)j] = input[(int)(i+j+1)];
                }
                l+=times * part2Counter(c2);
                i+= len;
            } else {
                if(readingLen) {
                    sb.append(cc);
                    continue;
                }
                if(readingTimes) {
                    sb.append(cc);
                    continue;
                }
                l++;
            }

        }
        return l;
    }

    public static void part2() {


        try {
            //char [] input = "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN".toCharArray();
            char [] input = readInput();
            long l = part2Counter(input);
            System.out.println("OK " + l);
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }


    }
       
}
