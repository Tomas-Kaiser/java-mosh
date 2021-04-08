package com.tomas.lambdas;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdasDemo {
    public static void show(){
        Predicate<String> hasLeftBrace = str -> str.startsWith("{");
        Predicate<String> hasRightBrace = str -> str.endsWith("}");

        // It returns one Predicate object
        Predicate<String> hasLeftAndRightBrace = hasRightBrace.and(hasLeftBrace);
        Boolean res = hasLeftAndRightBrace.test("{key=value}");
        System.out.println(res);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World!");
    }
}
