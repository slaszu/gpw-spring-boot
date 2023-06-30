package pl.slaszu.gpw.stock.application.ListStocks;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class StockViewModel implements Serializable {

    private String name;
    private String code;

}
