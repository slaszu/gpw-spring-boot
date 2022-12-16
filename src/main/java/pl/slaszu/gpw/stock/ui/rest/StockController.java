package pl.slaszu.gpw.stock.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stock.domain.Stock;
import pl.slaszu.gpw.stock.domain.StockRepository;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("")
    public List<Stock> getStocks() {

        return this.stockRepository.findAll();

    }
}
