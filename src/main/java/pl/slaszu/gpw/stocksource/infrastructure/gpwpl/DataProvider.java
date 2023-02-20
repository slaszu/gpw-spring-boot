package pl.slaszu.gpw.stocksource.infrastructure.gpwpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.application.FetchStocks.DataProviderInterface;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDto;
import pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider.ArchiveDataProvider;
import pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider.TodayDataProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataProvider implements DataProviderInterface {

    @Autowired
    private ArchiveDataProvider archiveDataProvider;

    @Autowired
    private TodayDataProvider todayDataProvider;

    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        if (date.getTime() > now.getTime()) {
            throw new FetchStocksException("Date must by equal today or earlier, you given date is later than today !");
        }

        if (format.format(date) == format.format(now)) {
            return this.todayDataProvider.getData(date);
        }

        return this.archiveDataProvider.getData(date);
    }
}
