package com.tomas.streams;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        Optional<Integer> sum = movies.stream()
                .map(m -> m.getLikes())
                .reduce((a, b) -> a + b); // or we can pass a reference to this method Integer::sum

        // This can throw an exception if it returns a null
        sum.get();
        // To prevent this we can use orElse where we supply a default value
        sum.orElse(0);
        System.out.println(sum.orElse(0));

    }
}
