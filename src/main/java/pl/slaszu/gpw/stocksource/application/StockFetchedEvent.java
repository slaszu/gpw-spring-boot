package pl.slaszu.gpw.stocksource.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockFetchedEvent {
    private StockDto stockDTO;
}
