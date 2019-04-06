package ru.otus.hw01;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class HelloOtus {

    public static void main(String... args) {
        String str = "abc, bcd,, cde   ,zsa";

        System.out.println(str);
        Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings().split(str);

        System.out.println(Joiner.on("; ").join(split));
    }
}