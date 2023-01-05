package pl.slaszu.gpw.stock.application.FetchStocks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockCommand;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockException;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockService;

@Service
@Slf4j
public class FetchStocksService {

    @Autowired
    private CreateStockService createStockService;

    public void fetch(DataProviderInterface dataProviderInterface) {
        dataProviderInterface.getData().forEach(
                (StockDTO stockDTO) -> {
                    FetchStocksService.log.info("Fetched stock code [x]".replace("x", stockDTO.getCode()));
                    try {
                        this.createStockService.create(new CreateStockCommand(stockDTO.getCode()));
                    } catch (CreateStockException e) {
                        FetchStocksService.log.info(e.getMessage());
                    }
                }
        );
    }
}
