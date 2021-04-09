package com.tomas.streams;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamsDemo {
    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10, Genre.THRILLER),
                new Movie("b", 15, Genre.ACTION),
                new Movie("c", 20, Genre.ACTION)
        );

        var result = movies.stream()
                        .collect(Collectors.partitioningBy(m -> m.getLikes() > 15,
                                                            Collectors.mapping(Movie::getTitle,
                                                            Collectors.joining(","))));

        System.out.println(result);
    }
}
