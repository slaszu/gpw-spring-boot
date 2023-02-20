package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider;

import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.application.FetchStocks.DataProviderInterface;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDto;

import java.util.Date;
import java.util.List;

@Service
public class TodayDataProvider implements DataProviderInterface {
    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {

        throw new FetchStocksException("Method not implement yet !");
        //return null;
    }
}
