package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stocksource.application.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocksService;
import pl.slaszu.gpw.stocksource.infrastructure.gpwpl.DataProvider;

import java.util.Date;

@RestController
@RequestMapping("/sources/gpwpl")
@Tag(name = "Gpw.pl")
public class GpwplController {

    @Autowired
    private DataProvider gpwplDataProvider;

    @Autowired
    private FetchStocksService fetchStocksService;

    @GetMapping({"/fetch"})
    @Operation(summary = "fetch stocks for today")
    public void fetchStocksToday() throws FetchStocksException {
        fetchStocksService.fetch(gpwplDataProvider, new Date());
    }

    @GetMapping({"/fetch/{date}"})
    @Operation(summary = "fetch stocks for archival date")
    public void fetchStocksForDate(
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date date
    ) throws FetchStocksException {
        fetchStocksService.fetch(gpwplDataProvider, date);
    }
}
