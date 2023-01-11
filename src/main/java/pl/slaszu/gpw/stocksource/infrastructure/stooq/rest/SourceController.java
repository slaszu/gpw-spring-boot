package pl.slaszu.gpw.stocksource.infrastructure.stooq.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksService;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDTO;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.DataProvider;

import java.util.List;

@RestController
@RequestMapping("/sources")
public class SourceController {

    @Autowired
    private DataProvider stooqDataProvider;

    @Autowired
    private FetchStocksService fetchStocksService;

    @GetMapping("/stooq")
    public void fetchStooq() throws FetchStocksException {
        fetchStocksService.fetch(stooqDataProvider);
    }

}
