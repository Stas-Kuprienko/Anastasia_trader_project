package com.anastasia.core_service.entity.markets;

import com.anastasia.trade_project.enums.Board;
import com.anastasia.trade_project.enums.Currency;
import com.anastasia.trade_project.enums.ExchangeMarket;
import com.anastasia.trade_project.enums.Market;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import java.util.Objects;

@Data
@RedisHash("stock")
public final class Stock extends Securities {

    private int lotSize;


    @Builder
    public Stock(String ticker,
                 String name,
                 Currency currency,
                 PriceAtTheDate price,
                 int lotSize,
                 long dayTradeVolume,
                 Market market,
                 Board board,
                 ExchangeMarket exchangeMarket) {
        this.ticker = ticker;
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.lotSize = lotSize;
        this.dayTradeVolume = dayTradeVolume;
        this.market = market;
        this.board = board;
        this.exchangeMarket = exchangeMarket;
    }

    public Stock() {}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock stock)) return false;
        return Objects.equals(ticker, stock.ticker) &&
                Objects.equals(name, stock.name) &&
                currency == stock.currency &&
                market == stock.market &&
                board == stock.board;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", lotSize=" + lotSize +
                ", currency=" + currency +
                ", market=" + market +
                ", board=" + board +
                ", exchangeMarket=" + exchangeMarket +
                '}';
    }
}