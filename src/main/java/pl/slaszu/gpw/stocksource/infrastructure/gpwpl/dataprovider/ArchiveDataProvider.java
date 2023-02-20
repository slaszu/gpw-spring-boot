package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.application.FetchStocks.DataProviderInterface;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ArchiveDataProvider implements DataProviderInterface {

    private String url;

    public ArchiveDataProvider(@Value("${gpw.gpwpl.url_archive}") String url) {
        this.url = url;
    }

    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        String currentUrl = this.url.replace("[date]", format.format(date));

        log.debug("Gpw.pl ");

        return null;
    }
}
