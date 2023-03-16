package pl.slaszu.gpw.stock.infrastructure.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stock.application.ListStocks.ListStocksQuery;
import pl.slaszu.gpw.stock.application.ListStocks.ListStocksService;
import pl.slaszu.gpw.stock.application.ListStocks.StockViewModel;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@Tag(name = "core")
public class StockController {

    @Autowired
    private ListStocksService listStocksService;

    @GetMapping("")
    public List<StockViewModel> getStocks() {

        ListStocksQuery query = new ListStocksQuery();
        return this.listStocksService.query(query);

    }
}
