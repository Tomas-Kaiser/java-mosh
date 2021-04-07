package com.tomas.lambdas;

public class LambdasDemo {
    public static void show(){
        greet(System.out::println);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World!");
    }
}
