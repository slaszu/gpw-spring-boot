package pl.slaszu.gpw.stocksource.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.sharedkernel.domain.EventDispatcherInterface;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockService;

import java.util.Date;

@Service
@Slf4j
public class FetchStocksService {

    @Autowired
    private CreateStockService createStockService;

    @Autowired
    private EventDispatcherInterface eventDispatcher;

    public void fetch(DataProviderInterface dataProviderInterface, Date date) throws FetchStocksException {
        dataProviderInterface.getData(date).forEach(
                (StockDto stockDTO) -> {
                    log.debug("Fetched stock code [x]".replace("x", stockDTO.getCode()));

                    this.eventDispatcher.dispatch(new StockFetchedEvent(stockDTO));
                }
        );
    }
}
