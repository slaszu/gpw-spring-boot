package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDto;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockFetchedEvent;

@Component
@Slf4j
public class CreateStockListener {

    @Autowired
    private CreateStockService createStockService;

    @EventListener
    public void whenStockFetched(StockFetchedEvent event) {
        StockDto stockDTO = event.getStockDTO();
        CreateStockCommand command = new CreateStockCommand(stockDTO.getCode());
        try {
            this.createStockService.create(command);
        } catch (CreateStockException e) {
            log.debug("Stock with code %s already exists".formatted(stockDTO.getCode()));
            return;
        }
        log.debug("Create whenStockFetched %s".formatted(stockDTO.getCode()));
    }
}
