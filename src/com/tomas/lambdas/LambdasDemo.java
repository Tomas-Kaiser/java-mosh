package com.tomas.lambdas;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdasDemo {
    public static void show(){
        Function<String, Integer> map = str -> str.length();
        int length = map.apply("Sky");
        System.out.println(length);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World!");
    }
}
