package pl.slaszu.gpw.stock.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.slaszu.gpw.shared.StockCreateEvent;
import pl.slaszu.gpw.stock.domain.Stock;
import pl.slaszu.gpw.stock.domain.StockRepository;

@Component
class StockCreateService {

    @Autowired
    private StockRepository stockRepository;

    public void create(StockCreateEvent event) {

        Stock stock = new Stock(event.getCode());
        this.stockRepository.save(stock);
    }
}
