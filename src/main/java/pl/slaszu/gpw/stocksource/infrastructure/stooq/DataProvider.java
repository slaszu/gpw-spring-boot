package pl.slaszu.gpw.stocksource.infrastructure.stooq;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.application.FetchStocks.DataProviderInterface;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDto;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModel;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModelRepository;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.service.StockDtoProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataProvider implements DataProviderInterface {

    private String urlWithPlaceholders;

    private StockDtoProvider stockDtoProvider;

    private HeaderViewModelRepository headerViewModelRepository;

    public DataProvider(
            @Value("${gpw.stooq.url}") String urlWithPlaceholders,
            StockDtoProvider stockDtoProvider,
            HeaderViewModelRepository headerViewModelRepository
    ) {
        this.urlWithPlaceholders = urlWithPlaceholders;
        this.stockDtoProvider = stockDtoProvider;
        this.headerViewModelRepository = headerViewModelRepository;
    }

    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {

        ArrayList<StockDto> stockCodes = new ArrayList<>();
        int pageNumber = 1;
        int counter = 0;

        do {
            String url = this.getUrlForPageNumber(pageNumber++, date);
            Document doc = this.getDocumentForUrl(url);
            Elements rows = doc.select("#fth1 tr");

            if (rows.isEmpty() && stockCodes.isEmpty()) {
                log.error(doc.body().toString());
                throw new FetchStocksException("Stooq does not return any data. Set new cookie and try again");
            }

            counter = 0;
            for (Element row : rows) {
                // find td elements in row
                Elements cells = row.select("td");
                // if not exists (header of table) continue
                if (cells.isEmpty()) {
                    continue;
                }
                stockCodes.add(
                        this.stockDtoProvider.fromTableCells(cells, date)
                );
                counter++;
            }

            log.debug(stockCodes.toString());

        } while (counter > 0);

        return stockCodes;
    }


    private Document getDocumentForUrl(String url) throws FetchStocksException {

        log.info(url);

        // TODO: 18.01.2023 przygotowac naglowki tylko raz
        List<HeaderViewModel> allHeaders = headerViewModelRepository.getAll();

        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36")
                    .followRedirects(true)
                    .headers(allHeaders.stream().collect(
                            Collectors.toMap(HeaderViewModel::getName, HeaderViewModel::getValue)
                    ))
                    .timeout(10000)
                    .get();
        } catch (IOException e) {
            throw new FetchStocksException("Page %s cant be fatched".formatted(url), e);
        }

    }

    private String getUrlForPageNumber(Integer pageNumber, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String dateString = sdf.format(date);

        return this.urlWithPlaceholders
                .replace("[page]", pageNumber.toString())
                .replace("[date]", dateString);
    }
}
