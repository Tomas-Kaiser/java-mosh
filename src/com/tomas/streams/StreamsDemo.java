package com.tomas.streams;

import java.util.List;

public class StreamsDemo {
    public static void show() {
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        // Imperative Programming
        int count = 0;
        for (Movie movie : movies){
            if (movie.getLikes() > 10)
                count++;
        }

        // Declarative (Functional) Programming
        movies.stream()
              .filter(movie -> movie.getLikes() > 10)
              .count(); // count will count how many movie pass the filter above.
    }
}
