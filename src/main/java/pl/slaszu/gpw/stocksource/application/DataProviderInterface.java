package pl.slaszu.gpw.stocksource.application;

import java.util.Date;
import java.util.List;

public interface DataProviderInterface {
    public List<StockDto> getData(Date date) throws FetchStocksException;
}
