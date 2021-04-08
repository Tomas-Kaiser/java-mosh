package com.tomas.streams;

import java.util.List;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void show() {
        Stream stream = Stream.generate(() -> Math.random());
        stream
             .limit(3)
             .forEach(System.out::println);
    }
}
