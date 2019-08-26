package com.jackie.blogasd.util;

import java.util.Random;

public class RandomUtil {
    public static String getCode() {
        String[] chars = new String[] {  "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += chars[new Random().nextInt(chars.length)];
        }
        return code;
    }
}
