package io.spring.workshop.stockquotes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;

import org.springframework.stereotype.Component;

@Component
public class QuoteGenerator {

	/**
	 * 保留两位小数
	 */
	private final MathContext mathContext = new MathContext(2);

	private final Random random = new Random();

	private final List<Quote> prices = new ArrayList<>();

	private final Flux<Quote> quoteStream;

	/**
	 * Bootstraps the generator with tickers and initial prices
	 */
	public QuoteGenerator() {
		// 1. 加载基准quote
		initializeQuotes();
		// 2. 随机动态生成价格波动的quote
		this.quoteStream = getQuoteStream();
	}

	public Flux<Quote> fetchQuoteStream() {
		return quoteStream;
	}

	private void initializeQuotes() {
		this.prices.add(new Quote("CTXS", 82.26));
		this.prices.add(new Quote("DELL", 63.74));
		this.prices.add(new Quote("GOOG", 847.24));
		this.prices.add(new Quote("MSFT", 65.11));
		this.prices.add(new Quote("ORCL", 45.71));
		this.prices.add(new Quote("RHT", 84.29));
		this.prices.add(new Quote("VMW", 92.21));
	}

	/**
	 * 定时产生流 (生产者)
	 */
	private Flux<Quote> getQuoteStream() {
		// 按照每200千分秒的速度发射Flux流
		return Flux.interval(Duration.ofMillis(200))
				// 背压处理方式为丢包
				.onBackpressureDrop()
				// 调用该方法将时间转换为price
				.map(this::generateQuotes)
				// 多个值flat成一个可以iterate的map
				.flatMapIterable(quotes -> quotes)
				// 将时间发射出去
				.share();
	}

	/**
	 * 随机根据已有Quote, 生成不同的Quote实体price
	 */
	private List<Quote> generateQuotes(long i) {
		Instant instant = Instant.now();
		return prices.stream()
				// 将标准quote映射为价格带有波动的quote
				.map(baseQuote -> {
					// 价格波动
					BigDecimal priceChange = baseQuote.getPrice()
							.multiply(new BigDecimal(0.05 * this.random.nextDouble()), this.mathContext);
					// 新实体
					Quote result = new Quote(baseQuote.getTicker(), baseQuote.getPrice().add(priceChange));
					result.setInstant(instant);
					return result;
				})
				.collect(Collectors.toList());
	}
}
