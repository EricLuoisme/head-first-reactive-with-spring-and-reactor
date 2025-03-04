package simpleTestCollections;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import simpleTestCollections.extra.User;

public class Part05Merge {

//========================================================================================

    /**
     * Merge would not guarantee the order, but concat must completely reading one stream then another
     */

    // Merge flux1 and flux2 values with interleave
    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.mergeWith(flux2);
    }

//========================================================================================

    // Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.concatWith(flux2);
    }

//========================================================================================

    // Create a Flux containing the value of mono1 then the value of mono2
    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
        return Flux.concat(mono1, mono2);
    }

}
