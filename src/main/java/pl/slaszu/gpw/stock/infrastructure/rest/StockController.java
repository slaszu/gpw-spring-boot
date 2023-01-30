package pl.slaszu.gpw.stock.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockService;
import pl.slaszu.gpw.stock.application.ListStocks.ListStocksQuery;
import pl.slaszu.gpw.stock.application.ListStocks.ListStocksService;
import pl.slaszu.gpw.stock.application.ListStocks.StockViewModel;

import java.util.List;

// TODO: 27.01.2023 add stock with price endpoint /stocks/{code}/{date|optional} 

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private CreateStockService createStockService;

    @Autowired
    private ListStocksService listStocksService;

    @GetMapping("")
    public List<StockViewModel> getStocks() {

        ListStocksQuery query = new ListStocksQuery();
        return this.listStocksService.query(query);

    }
}
