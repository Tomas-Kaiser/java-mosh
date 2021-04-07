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