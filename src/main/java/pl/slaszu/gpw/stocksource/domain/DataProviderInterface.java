package pl.slaszu.gpw.stocksource.domain;

import pl.slaszu.gpw.stocksource.domain.exception.FetchStocksException;
import pl.slaszu.gpw.stocksource.domain.exception.FreeDayException;

import java.util.Date;
import java.util.List;

public interface DataProviderInterface {
    public List<StockDto> getData(Date date) throws FetchStocksException;
}
