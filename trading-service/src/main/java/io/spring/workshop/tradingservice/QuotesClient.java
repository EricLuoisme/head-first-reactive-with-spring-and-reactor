package io.spring.workshop.tradingservice;


import io.spring.workshop.stockquotes.Quote;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Component
public class QuotesClient {

    private WebClient webClient = WebClient.builder().build();

    public Flux<Quote> quotesFeed() {
        Flux<Quote> quotes = webClient
                .get()
                .uri("http://localhost:8081/quotes")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Quote.class)
                .log();
        return quotes;
    }

}
