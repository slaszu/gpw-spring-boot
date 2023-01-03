package pl.slaszu.gpw.stock.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockCommand;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockException;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockService;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.model.StockViewModel;
import pl.slaszu.gpw.stock.domain.repository.StockViewModelRepositoryInterface;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private CreateStockService createStockService;

    @Autowired
    private StockViewModelRepositoryInterface stockViewModelRepository;

    @GetMapping("")
    public List<StockViewModel> getStocks() {

        return this.stockViewModelRepository.getAll();

    }

    @GetMapping("/add/{code}")
    public void addRandomStock(@PathVariable String code) throws CreateStockException {

        CreateStockCommand command = new CreateStockCommand(code);
        this.createStockService.create(command);

    }
}
