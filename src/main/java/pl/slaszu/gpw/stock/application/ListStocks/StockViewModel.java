package pl.slaszu.gpw.stock.application.ListStocks;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockViewModel {

    private String name;
    private String code;

}
