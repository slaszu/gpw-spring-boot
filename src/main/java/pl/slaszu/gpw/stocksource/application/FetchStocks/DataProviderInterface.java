package pl.slaszu.gpw.stocksource.application.FetchStocks;

import java.util.List;

public interface DataProviderInterface {
    public List<StockDTO> getData() throws FetchStocksException;
}
