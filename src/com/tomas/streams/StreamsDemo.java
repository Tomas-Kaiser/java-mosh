package com.tomas.streams;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void show() {
        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 15),
                new Movie("c", 20)
        );

        movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle))
                .forEach(m -> System.out.println(m));
}
}
