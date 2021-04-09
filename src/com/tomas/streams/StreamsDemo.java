package com.tomas.streams;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        var result = movies.stream()
                .filter(m -> m.getLikes() > 10)
                // Function.identity returns the object it self (in this case the movie) instead of writing
                // m -> m
//                .collect(Collectors.toMap(m -> m.getTitle(), Function.identity()));
                // to summarizing
                .collect(Collectors.summarizingInt(Movie::getLikes));
        System.out.println(result);

    }
}
