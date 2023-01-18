package pl.slaszu.gpw.stocksource.infrastructure.stooq.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksService;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.DataProvider;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModelRepository;

import java.util.Date;

@RestController
@RequestMapping("/sources/stooq")
public class StooqController {

    @Autowired
    private DataProvider stooqDataProvider;

    @Autowired
    private FetchStocksService fetchStocksService;

    @Autowired
    private HeaderViewModelRepository headerViewModelRepository;

    @GetMapping({"/fetch", "/fetch/{date}"})
    public void fetchStooq(@PathVariable( required = false) Date date) throws FetchStocksException {
        if (date == null) {
            date = new Date();
        }
        fetchStocksService.fetch(stooqDataProvider, date);
    }
}
