package com.tomas.lambdas;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LambdasDemo {
    public static void show(){
        Supplier<Double> getRandom = () -> Math.random();
        double random = getRandom.get();
        System.out.println(random);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World!");
    }
}
