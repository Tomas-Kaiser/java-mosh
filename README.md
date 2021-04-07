# Lambda Expression

Lambda expression was introduced in java 8 to allow us to use functional
programming techniques in java. We are talking about:

- Functional Interfaces
- Lambda Expressions
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


