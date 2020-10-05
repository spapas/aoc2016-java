package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7 {
    static boolean hasABBA(String s) {
        int i;

        for(i=0;i<s.length()-3;i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i+1);
            char c3 = s.charAt(i+2);
            char c4 = s.charAt(i+3);
            if(c1 == c4 && c2 == c3 && c1 != c2) return true;
        }
        return false;
    }

    static ArrayList<String> getABABAB(String s) {
        ArrayList<String> res = new ArrayList<String>();
        int i;
        for(i=0;i<s.length()-2;i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i+1);
            char c3 = s.charAt(i+2);

            if(c1 == c3 && c1 != c2) {
                StringBuilder sb = new StringBuilder();
                sb.append(c1);
                sb.append(c2);
                sb.append(c3);
                res.add(sb.toString());
            };
        }
        return res;
    }

    static boolean getAbabOk(String s1, String s2) {
        return s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0);
    }

    record Address(ArrayList<String> inBrackets, ArrayList<String> outsideBrackets) {
        public boolean supportsTLS() {

            for(String s: inBrackets) {
                if(hasABBA(s)) return false;
            }

            for(String s: outsideBrackets) {
                if(hasABBA(s)) return true;
            }
            return false;
        }

        public boolean supportsSSL() {
            ArrayList<String> aba = new ArrayList<>();
            ArrayList<String> aba2 = new ArrayList<>();

            for(String s: inBrackets) {
                aba.addAll(getABABAB(s));
            }

            for(String s: outsideBrackets) {
                aba2.addAll(getABABAB(s));
            }

            for(String ab2: aba2) {
                for(String ab: aba) {
                    if(getAbabOk(ab, ab2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static Address getAddress(String s) {
            int i;
            boolean readingInBrackets = false;
            StringBuilder sb = new StringBuilder();
            ArrayList<String> ib = new ArrayList<String>();
            ArrayList<String> ob = new ArrayList<String>();

            for(i=0;i<s.length();i++) {
                char c = s.charAt(i);
                if(c == '[') {
                    readingInBrackets = true;
                    ob.add(sb.toString());
                    sb = new StringBuilder();
                } else if (c == ']') {
                    readingInBrackets = false;
                    ib.add(sb.toString());
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
            ob.add(sb.toString());
            return new Address(ib, ob);
        }
    }

    static ArrayList<Address> readInput() throws IOException {
        //File file = new File("c:\\progr\\java\\aoc2016-java\\day2.txt");
        final File file = new File("day7.txt");

        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l;
        final ArrayList<Address> res = new ArrayList<Address>();
        while( (l= br.readLine())!=null){
            res.add(Address.getAddress(l));
        }
        return res;
    }



    public static void part1() {
        try {
            ArrayList<Address> addresses = readInput();
            //System.out.println(addresses.size());
            //System.out.println(hasABBA("iooooj"));
            int cnt = 0;

            for(Address a: addresses) {
                if(a.supportsTLS()) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void part2() {
        try {
            System.out.println(Address.getAddress("rvvyvofaygynnetjtry[kegzdkleyezldyeyn]erioueyndgksxetku[tsarhnyrbaubgmteiw]lbcsksdiqqdacutvc").supportsSSL());
            // 339 too high
            ArrayList<Address> addresses = readInput();
            int cnt = 0;

            for(Address a: addresses) {
                if(a.supportsSSL()) {
                    cnt++;
                }
            }
            System.out.println(cnt);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
}
