package com.tomas.lambdas;

import java.util.List;

public class LambdasDemo {
    public static void show(){
        greet(System.out::println);

        List<Integer> list = List.of(1,2,3);

        for (Integer item : list)
            System.out.println(item);

        list.forEach(item -> System.out.println(item));

        list.forEach(System.out::println);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World!");
    }
}
