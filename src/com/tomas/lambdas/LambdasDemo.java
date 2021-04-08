package com.tomas.lambdas;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdasDemo {
    public static void show(){
        Predicate<String> isLongerThan5 = str -> str.length() > 5;
        boolean res = isLongerThan5.test("sky");
        System.out.println(res);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World!");
    }
}
