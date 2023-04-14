package pl.slaszu.gpw.stock.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "get all stocks")
    public List<StockViewModel> getAllStocks() {
        return this.listStocksService.getAllStocks();
    }
    
    public List<StockViewModel> getStocksByNameOrCode() {
        // TODO: 14/04/2023 wyszukiwanie stockow
        return null;
    }
}
