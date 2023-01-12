package pl.slaszu.gpw.stocksource.application.FetchStocks;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockFetchedEvent {
    private StockDTO stockDTO;
}
