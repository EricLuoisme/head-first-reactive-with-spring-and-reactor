package simpleTestCollections;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import simpleTestCollections.extra.User;

public class Part04Transform {

//========================================================================================

    // Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(x -> new User(
                x.getUsername().toUpperCase(),
                x.getFirstname().toUpperCase(),
                x.getLastname().toUpperCase()));
    }

//========================================================================================

    // Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(x -> new User(
                x.getUsername().toUpperCase(),
                x.getFirstname().toUpperCase(),
                x.getLastname().toUpperCase()));
    }

//========================================================================================

    // Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(this::asyncCapitalizeUser);
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}
