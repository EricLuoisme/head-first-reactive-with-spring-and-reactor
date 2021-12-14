package io.spring.workshop.stockquotes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    /**
     * 保留2位小数
     */
    private static final MathContext MATH_CONTEXT = new MathContext(2);

    /**
     * 检票机
     */
    private String ticker;

    /**
     * 票价
     */
    private BigDecimal price;

    /**
     * 购票时间
     */
    private Instant instant = Instant.now();


    public Quote(String ticker, Double price) {
        this.ticker = ticker;
        this.price = new BigDecimal(price, MATH_CONTEXT);
    }

    public Quote(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

}