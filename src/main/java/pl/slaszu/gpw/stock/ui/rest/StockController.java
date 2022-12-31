package pl.slaszu.gpw.stock.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.repository.StockRepositoryInterface;
import pl.slaszu.gpw.stock.domain.model.StockViewModel;
import pl.slaszu.gpw.stock.domain.repository.StockViewModelRepositoryInterface;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockRepositoryInterface stockRepository;

    @Autowired
    private StockViewModelRepositoryInterface stockViewModelRepository;

    @GetMapping("")
    public List<StockViewModel> getStocks() {

        return this.stockViewModelRepository.getAll();

    }

    @GetMapping("/add/{code}")
    public Stock addRandomStock(@PathVariable String code) {
        Stock stock = new Stock(code);
        this.stockRepository.save(stock);

        return stock;
    }
}
