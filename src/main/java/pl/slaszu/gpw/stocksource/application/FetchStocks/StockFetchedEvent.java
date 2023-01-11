package pl.slaszu.gpw.stocksource.application.FetchStocks;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO: 11.01.2023 event triggered when stock has been featched
@Getter
@AllArgsConstructor
public class StockFetchedEvent {
    private StockDTO stockDTO;
}
