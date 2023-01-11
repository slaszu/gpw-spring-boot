package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDTO;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockFetchedEvent;

@Component
@Slf4j
public class CreateStockListener {

    @Autowired
    private CreateStockService createStockService;

    @EventListener
    public void whenStockFetched(StockFetchedEvent event) throws CreateStockException {
        StockDTO stockDTO = event.getStockDTO();

        CreateStockCommand command = new CreateStockCommand(stockDTO.getCode());
        this.createStockService.create(command);
        log.info("Create whenStockFetched %s".formatted(stockDTO.getCode()));
    }
}
