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

        // res is a Map<Genre, List<Movie>>
        var res1 = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre));

        // we can supply the second argument
        // res is a Map<Genre, Set<Movie>>
        var res2 = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.toSet()));

        // to count each movie of each genre
        var res3 = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.counting()));
        // output: {THRILLER=1, ACTION=2}

        // We can also join movies
        var res4 = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre,
                         Collectors.mapping(Movie::getTitle,
                                            Collectors.joining(","))));
        // output: {THRILLER=a, ACTION=b,c}

        System.out.println(res1);
    }
}
