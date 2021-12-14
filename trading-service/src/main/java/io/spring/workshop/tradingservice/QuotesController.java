package io.spring.workshop.tradingservice;

import io.spring.workshop.stockquotes.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;

@Controller
public class QuotesController {

    @Autowired
    private QuotesClient quotesClient;

    @GetMapping("/quotes/feed")
    public Mono<ServerResponse> consumeFeed() {
        return ServerResponse.ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(quotesClient.quotesFeed(), Quote.class);
    }
}
