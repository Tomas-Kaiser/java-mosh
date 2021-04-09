# Lambda Expression

Lambda expression was introduced in java 8 to allow us to use functional
programming techniques in java. We are talking about:

- Lambda Expressions
- Functional Interfaces
    - Consumers
    - Suppliers
    - Functions
    - Predicates
    
## Functional Interface

A functional interface is an interface with a single abstract method. Does not
matter if the interface contains default or static method unless it has just one
abstract method.

### Anonymous Inner Class

We can use anonymous inner class when we want to use it just once
so that we do not have to create a java class for that.

`Printer` is an interface
`ConsolePrinter` is an implementation of the Printer interface

```
public static void show(){
    greet(new ConsolePrinter());
}

// Instead of using ConsolePrinter() class we can do as follow with
// anonymous inner class:
public static void show(){
    greet(new Printer() {
        @Override
        public void print(String message) {
            System.out.println(message);
        }
    });
}
```

## Lambda Expression

Lambda expression is like anonymous function that we can pass around.

Short version:
`greet(message -> System.out.println(message));`

Long version:
```
greet((String message) -> {
    System.out.println(message);
});
```
We do not need specify type of message so we can remove `String` also we do not
need () since we have just one parameter and if the body contains just one line
we can remove {} and put it on one line. `->` is a lambda operator.

See comparision between lambda expression and anonymous inner class:

```
public static void show(){
    // Lambda expression
    greet(message -> System.out.println(message));

    // Anonymous inner class
    greet(new Printer() {
        @Override
        public void print(String message) {
            System.out.println(message);
        }
    });
}
```

We can also store a lambda expression in a variable
`Printer printer = message -> System.out.print(message);`

### Method References

It easier to read and write lambda expression.

Instead of `greet(message -> System.out.println(message));` we can write
only `greet(System.out::println);`

The pattern is `Class/Object::method`

`greet(LambdasDemo::new)` is calling a constructor of the LambdasDemo class.

## Built-in Functional Interfaces

Java provides many predefine functions interfaces that we can use to perform
common tasks [Package java.util.function](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/package-summary.html).

There much more functions but basically we have just 4 types of functional
interfaces in java:

- Consumer
    - The consumer interface represents an operation that takes a single argument
    and returns no result. Consumer => consume a value.
- Supply
    - It is an opposite of consumer interface. It represents an operation that
    takes no input and returns a value.
- Function
    - Represents a function that map a value to different value.
- Predicate
    - Represents an operation that takes an object and check the see if that 
    object satisfies some criteria. We use this for filtering data eg.: we can
    get customers who do not have a phone number.

### Consumer Interface

The consumer interface represents an operation that takes a single argument
and does not return a value.

```
list<Integer> list = List.of(1,2,3);

// Imperative programming (for, if/else, switch). How something should be done
for (var item : list)
    System.out.println(item);
    
// Declerative programming. What needs to be done
list.forEach(item -> System.out.println(item));

// Or we can use method reference here
list.forEach(System.out::println);

```
 
 #### Chaining Consumer
 
 We can chain consumer as follow:
 
```
 List<String> list = List.of("a", "b", "c");
 Consumer<String> print = item -> System.out.println(item);
 Consumer<String> printUpperCase = item -> System.out.println(item.toUpperCase());
 
 list.forEach(print.andThen(printUpperCase));
 
 Output:
 a
 A
 b
 B
 c
 C
```

### Supplier Interface

Is an opposite of consume interface. It supplies a value.

```
Supplier<Double> getRandom = () -> Math.random();
double random = getRandom.get();
System.out.println(random);
```

The function `() -> Math.random()` is not executed until we explicitly we call it
`getRandom.get()`. This is called a lazy evaluation.

### Function Interface

The function interface represents a function that map a value to different value.

``` 
Function<String, Integer> map = str -> str.length();
int length = map.apply("Sky");
System.out.println(length);
```

#### Composing Functions

```
// key:value
// fist: key=value
// second: {key=value}
Function<String, String> replaceColon = str -> str.replace(":", "=");
Function<String, String> addBraces = str -> "{" + str + "}";

// First version of declarative programming
String res = replaceColon.andThen(addBraces).apply("key:value");
System.out.println(res);

// Second version using compose in reverse order than above so we have to switch order starting with addBrace
String result = addBraces.compose(replaceColon).apply("key:value");
System.out.println(result);
```

### Predicate Interface

It is very useful interface. It accepts one argument and returns boolean.

```
Predicate<String> isLongerThan5 = str -> str.length() > 5;
boolean res = isLongerThan5.test("sky");
System.out.println(res);
```

Combining predicates:

```
Predicate<String> hasLeftBrace = str -> str.startsWith("{");
Predicate<String> hasRightBrace = str -> str.endsWith("}");

// It returns one Predicate object
Predicate<String> hasLeftAndRightBrace = hasRightBrace.and(hasLeftBrace);
Boolean res = hasLeftAndRightBrace.test("{key=value}");
System.out.println(res);
```

# Streams

It allows us to process collection of data in a declarative way.

- Overview of Streams
- Mapping
- Filtering
- Slicing
- Sorting
- Reducing
- Colletors

Every collection in java has a method stream.

Example of using streams:

``` 
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
```

## Creating Streams

We have several way to create streams:

- From collection
- Form arrays
- From an arbitrary number of objects `Stream.of(1,2,3);`
- Infinite/finite streams `Stream.generate(() -> Math.random());`

To generate just three random number:
``` 
Stream stream = Stream.generate(() -> Math.random());
stream
   .limit(3)
   .forEach(n -> System.out.println(n));
```

Every class that implements the collection interface has the ability to return
a stream.

If we have `int[] numbers` then we have to use
`Arrays.stream(numbers).forEach(n -> System.out.println(n)`.

 ## Mapping
 
 To transform the value in a string. To do that we use:
 
 - map()
 - flatMap()
 
 ``` 
movies.stream()
           .map(movie -> movie.getTitle())
           .forEach(title -> System.out.println(title));
 
// Using flatMap when we have two collections
Stream.of(List.of(1,2,3), List.of(4,5,6))
      .flatMap(list -> list.stream())
      .forEach(n -> System.out.println(n));
 ```
 
 ## Filtering
 
 We have two kind of operation:
 - Intermediate (map, filter ...). Those returns a new Stream
 - Terminal (forEach). This terminate a Stream.
 
 ``` 
Predicate<Movie> isPopular = movie -> movie.getLikes() > 10;
movies.stream()
      .filter(isPopular)
      .forEach(m -> System.out.println(m.getTitle()));
 ```
 
 ## Slicing
 
 We have several method to slice a stream:
 
 - limit(n)
 - skip(n)
 - takeWhile(predicate)
 - dropWhile(predicate)
 
 ``` 
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
 ```
 
 The difference between takeWhile & filter is that the filter method iterates through
 out the whole list but takeWhile is iterating through a list until the condition
 is met. Once it returns false it terminates the iteration.
 
 The dropWhile method is an opposite of takeWhile.
 
 ## Sorting
 
 To sort eg. the movies we have two options:

1. we can implement an comparable interface into movie and then use `.sorted()`
method
2. we can give provide an comparable interface as an argument see more below

``` 
movies.stream()
       // This is a one way or we can use static method Comparator
       // .sorted((a, b) -> a.getTitle().compareTo(b.getTitle()))
        .sorted(Comparator.comparing(m -> m.getTitle()))
        .forEach(m -> System.out.println(m));
```

Instead of using `m -> m.getTitle()` we can use method reference
`Movie::getTitle`

## Getting Unique Elements

To get only unique values we can use `.distinct()`

```  
movies.stream()
         .map(Movie::getLikes)
         .distinct()
         .forEach(System.out::println);
```

## Peek Elements

We use peek for troubleshooting like below:

``` 
movies.stream()
         .filter(movie -> movie.getLikes() > 10)
         .peek(movie -> System.out.println("filtered: " + movie.getTitle()))
         .map(Movie::getTitle)
         .peek(title -> System.out.println("mapped: " + title))
         .forEach(System.out::println);
```

## Reducers

Reducers is a group of operations. It basically reduce the stream of objects
to single object which is an answer we are looking for. For example:

- `count()` count elements in the stream so this it reduces stream to a number
of elements of that stream
- `anyMatch(predicate)`
- `allMatch(predicate)`
- `noneMatch(predicate)`
- `findFirst`
- `findAny`
- `max(comparator)`
- `min(comparator)`

All the above operations are terminate operations it produces the result.

## Reducing a Stream

To sum all likes we can use reduce:

```  
Optional<Integer> sum = movies.stream()
          .map(m -> m.getLikes())
          .reduce((a, b) -> a + b);
          // or we can pass a reference to this method Integer::sum

// This can throw an exception if it returns a null
sum.get();
// To prevent this we can use orElse where we supply a default value
sum.orElse(0);
System.out.println(sum.orElse(0));
```

## Collectors

We use Collectors to convert output of the stream to a data structure

```  
var result = movies.stream()
      .filter(m -> m.getLikes() > 10)
      // Function.identity returns the object it self (in this case the movie) instead of writing
      // m -> m
//      .collect(Collectors.toMap(m -> m.getTitle(), Function.identity()));
      // to summarizing
        .collect(Collectors.summarizingInt(Movie::getLikes));
      
      System.out.println(result);
```