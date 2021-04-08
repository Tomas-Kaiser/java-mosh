package com.tomas.streams;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        movies.stream()
                .map(movie -> movie.getTitle())
                .forEach(title -> System.out.println(title));

        // Using flatMap when we have two collections
        Stream.of(List.of(1,2,3), List.of(4,5,6))
                .flatMap(list -> list.stream())
                .forEach(n -> System.out.println(n));
    }
}
