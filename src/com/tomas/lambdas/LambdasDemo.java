package com.tomas.lambdas;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdasDemo {
    public static void show(){
//        key:value
//        fist: key=value
//        second: {key=value}
        Function<String, String> replaceColon = str -> str.replace(":", "=");
        Function<String, String> addBraces = str -> "{" + str + "}";

        // First version of declarative programming
        String res = replaceColon.andThen(addBraces).apply("key:value");
        System.out.println(res);

        // Second version using compose in reverse order than above so we have to switch order starting with addBrace
        String result = addBraces.compose(replaceColon).apply("key:value");
        System.out.println(result);
    }

    public static void greet(Printer printer) {
        printer.print("Hello World!");
    }
}
