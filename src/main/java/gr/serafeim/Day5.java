package gr.serafeim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5 {
    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static void part1() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int cnt = 0;
            int tot = 0;
            while(true) {

                String s = "cxdnnyjw" + cnt;
                byte[] digest = md5.digest(s.getBytes());
                String ss = toHexString(digest);
                if(ss.startsWith("00000")) {
                    System.out.println(ss.charAt(5));
                    tot++;
                    if(tot == 8) break;
                }
                cnt++;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



}
