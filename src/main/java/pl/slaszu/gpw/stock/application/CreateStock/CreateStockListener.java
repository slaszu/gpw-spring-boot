package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.slaszu.gpw.stocksource.application.StockDto;
import pl.slaszu.gpw.stocksource.application.StockFetchedEvent;

@Component
@Slf4j
public class CreateStockListener {

    @Autowired
    private CreateStockService createStockService;

    @EventListener
    public void whenStockFetched(StockFetchedEvent event) {
        StockDto stockDTO = event.getStockDTO();

        CreateStockPriceCommand stockPriceCommand = new CreateStockPriceCommand(
                stockDTO.getPriceOpen(),
                stockDTO.getPriceHigh(),
                stockDTO.getPriceLow(),
                stockDTO.getPrice(),
                stockDTO.getVolume(),
                stockDTO.getAmount(),
                stockDTO.getDate()
        );

        CreateStockCommand command = new CreateStockCommand(stockDTO.getCode(), stockDTO.getName(), stockPriceCommand);

        this.createStockService.create(command);
    }
}
