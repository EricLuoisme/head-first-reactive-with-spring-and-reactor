package simpleTestCollections;

// generic imports to help with simpler IDEs (ie tech.io)
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.*;

import reactor.core.publisher.Flux;

public class Part01Flux {

//========================================================================================

    // Return an empty Flux
    Flux<String> emptyFlux() {
        return Flux.empty();
    }

//========================================================================================

    // Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar");
    }

//========================================================================================

    // Create a Flux from a List that contains 2 values "foo" and "bar"
    Flux<String> fooBarFluxFromList() {
        ArrayList<String> a = new ArrayList<>(Arrays.asList("foo", "bar"));
        return Flux.fromIterable(a);
    }

//========================================================================================

    // Create a Flux that emits an IllegalStateException
    Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException());
    }

//========================================================================================

    // Create a Flux that emits increasing values from 0 to 9 each 100ms
    Flux<Long> counter() {
        Duration period = Duration.of(100L, ChronoUnit.MILLIS);
        return Flux.interval(period).take(10L).log();
    }

    public static void main(String[] args) {

        Part01Flux test = new Part01Flux();
        test.counter().subscribe();

    }
}

