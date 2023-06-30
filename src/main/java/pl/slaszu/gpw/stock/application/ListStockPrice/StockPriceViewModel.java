package pl.slaszu.gpw.stock.application.ListStockPrice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.slaszu.gpw.stock.domain.model.StockPrice;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StockPriceViewModel implements Serializable {

    private Float priceOpen;

    private Float priceHigh;

    private Float priceLow;

    private Float price;

    private Integer volume;

    private Integer amount;

    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Europe/Warsaw")
    private Date updatedAt;

    public static StockPriceViewModel fromStockPrice(StockPrice stockPrice) {
        return new StockPriceViewModel(
            stockPrice.getPriceOpen(),
            stockPrice.getPriceHigh(),
            stockPrice.getPriceLow(),
            stockPrice.getPrice(),
            stockPrice.getVolume(),
            stockPrice.getAmount(),
            stockPrice.getDate(),
            stockPrice.getUpdatedAt()
        );
    }
}
