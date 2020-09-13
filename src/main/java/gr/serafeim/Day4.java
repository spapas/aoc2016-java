package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {
    record Room(String name, int id, String checksum) {
        public boolean isReal() {
            Map<Character, Long> freqs = name.chars().mapToObj(
                    i -> (char) i
            ).filter(c -> c != '-').collect(
                    Collectors.groupingBy(Function.identity(), Collectors.counting())
            );
            List<Map.Entry<Character, Long>> entries = freqs.entrySet().stream().sorted(Comparator.comparing(
                    (Map.Entry<Character, Long> m) -> m.getValue()).reversed().thenComparing(
                    (Map.Entry<Character, Long> m) -> m.getKey()
            )).limit(5).collect(Collectors.toList());

            String testcheck= entries.stream().map(m -> m.getKey().toString()).collect(Collectors.joining(""));
            System.out.println(testcheck);
            return testcheck.equals(this.checksum);
        }
    }

    static Room parseRoom(String s) {
        String regex = "((\\D+)-(\\d+)\\[(\\D+)\\])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        matcher.find();

        String g1= matcher.group(1);
        String g2= matcher.group(2);
        String g3= matcher.group(3);
        String g4= matcher.group(4);
        return new Room(g2, Integer.parseInt(g3), g4);

    }

    static ArrayList<Room> readInput() throws IOException {
        final File file = new File("day4.txt");
        final BufferedReader br = new BufferedReader(new FileReader(file));
        String l;
        ArrayList<Room> res = new ArrayList<>();

        while ((l = br.readLine()) != null) {
            res.add(parseRoom(l));
        }

        return res;
    }



    public static void part1() {
        try {
            ArrayList<Room> rooms = readInput();
            int res = Util.foldLeft(rooms.stream().filter(Room::isReal), 0, (acc, el) -> el.id + acc );
            System.out.println(res);
            //System.out.println(parseRoom("shoewudys-tou-ixyffydw-478[uszty]").isReal());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
