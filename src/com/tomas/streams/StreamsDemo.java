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

        // Pagination solution
        // We have 1000 movies
        // We want to see 10 movies per page
        // We are in third page
        // skip(20) = skip( (page - 1) x pageSize )
        // limit(10) = limit(pageSize)

        movies.stream()
                .skip(20)
                .limit(10)
                .forEach(m -> System.out.println(m.getTitle()));

        movies.stream()
            .takeWhile(movie -> movie.getLikes() < 30)
            .forEach(movie -> movie.getTitle());
}
}
