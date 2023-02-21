package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksService;
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

    @GetMapping({"/fetch", "/fetch/{date}"})
    public void fetchStooq(
            @PathVariable(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date date
    ) throws FetchStocksException {
        if (date == null) {
            date = new Date();
        }
        fetchStocksService.fetch(gpwplDataProvider, date);
    }
}
