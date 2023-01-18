package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CreateStockPriceCommand {

    private Float priceOpen;

    private Float priceHigh;

    private Float priceLow;

    private Float price;

    private Integer volume;

    private Integer amount;

    private Date date;
}
