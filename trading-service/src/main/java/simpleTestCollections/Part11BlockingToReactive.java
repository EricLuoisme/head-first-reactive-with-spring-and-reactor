package simpleTestCollections;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import simpleTestCollections.extra.BlockingRepository;
import simpleTestCollections.extra.User;

public class Part11BlockingToReactive {

//========================================================================================

    // Create a Flux for reading all users from the blocking repository deferred until the flux is subscribed, and run it with an elastic scheduler
    Flux<User> blockingRepositoryToFlux(BlockingRepository<User> repository) {
        return Flux.defer(
                () -> Flux.fromIterable(repository.findAll()))
                .subscribeOn(Schedulers.elastic());
    }

//========================================================================================

    // Insert users contained in the Flux parameter in the blocking repository using an elastic scheduler
    // and return a Mono<Void> that signal the end of the operation
    Mono<Void> fluxToBlockingRepository(Flux<User> flux, BlockingRepository<User> repository) {
        return flux.publishOn(Schedulers.elastic()).doOnNext(repository::save).then();
    }

}
