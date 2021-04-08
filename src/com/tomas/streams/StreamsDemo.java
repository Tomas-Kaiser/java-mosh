package com.tomas.streams;

import java.sql.SQLOutput;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        Predicate<Movie> isPopular = movie -> movie.getLikes() > 10;
        movies.stream()
                .filter(isPopular)
                .forEach(m -> System.out.println(m.getTitle()));
    }
}
