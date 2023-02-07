package pl.slaszu.gpw.stocksource.infrastructure.stooq.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksService;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.DataProvider;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModelRepository;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.service.DateBetweenProvider;

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

    @Autowired
    private DateBetweenProvider dateBetweenProvider;

    @GetMapping({"/fetch", "/fetch/{date}"})
    public void fetchStooq(
            @PathVariable(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date date
    ) throws FetchStocksException {
        if (date == null) {
            date = new Date();
        }
        fetchStocksService.fetch(stooqDataProvider, date);
    }

    @GetMapping({"/fetch/range/{dateFrom}", "/fetch/range/{dateFrom}/{dateTo}"})
    public void fetchRangeStooq(
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date dateFrom,
            @PathVariable(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date dateTo
    ) throws Exception {
        if (dateTo == null) {
            dateTo = new Date();
        }

        for (Date date : this.dateBetweenProvider.getRangeBetween(dateFrom, dateTo)) {
            fetchStocksService.fetch(stooqDataProvider, date);
        }
    }
}
