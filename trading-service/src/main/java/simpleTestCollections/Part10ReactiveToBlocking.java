package simpleTestCollections;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import simpleTestCollections.extra.User;

public class Part10ReactiveToBlocking {

    //========================================================================================

    // Return the user contained in that Mono
    User monoToValue(Mono<User> mono) {
        return mono.block();
    }

//========================================================================================

    // Return the users contained in that Flux
    Iterable<User> fluxToValues(Flux<User> flux) {
        return flux.toIterable();
    }

}
