package com.teamjuno.truthtable2.Utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringSplitter {

    public static String splitter(String expression) {
        String[] gg = expression.split("[()]");
        return Arrays.stream(gg).collect(Collectors.joining());
    }
}
