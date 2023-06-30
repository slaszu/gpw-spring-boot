package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stocksource.domain.exception.FetchStocksException;
import pl.slaszu.gpw.stocksource.domain.FetchStocksService;
import pl.slaszu.gpw.stocksource.domain.exception.FreeDayException;
import pl.slaszu.gpw.stocksource.infrastructure.gpwpl.DataProvider;

import java.util.Calendar;
import java.util.Date;

@Slf4j
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
        @PathVariable
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date date
    ) throws FetchStocksException {
        fetchStocksService.fetch(gpwplDataProvider, date);
    }

    @GetMapping({"/fetch_range/{dateFrom}/{dateTo}"})
    @Operation(summary = "fetch stocks range for archival date from date to date")
    public void fetchStocksRange(
        @PathVariable
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date dateFrom,
        @PathVariable
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date dateTo
    ) throws FetchStocksException {
        Calendar start = Calendar.getInstance();
        start.setTime(dateFrom);
        Calendar end = Calendar.getInstance();
        end.setTime(dateTo);

        for (Date date = start.getTime(); start.before(end) || start.equals(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            try {
                fetchStocksService.fetch(gpwplDataProvider, date);
            } catch (FreeDayException e) {
                log.warn(e.getMessage());
            }
        }
    }
}
