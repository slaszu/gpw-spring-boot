package pl.slaszu.gpw.stocksource.application.FetchStocks;

import java.util.List;

public interface DataProviderInterface {
    public List<StockDto> getData() throws FetchStocksException;
}
