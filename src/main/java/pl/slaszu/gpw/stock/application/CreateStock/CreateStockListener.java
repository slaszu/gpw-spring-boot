package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.slaszu.gpw.stock.application.CreateStock.Event.StockChangedEvent;
import pl.slaszu.gpw.stock.application.CreateStock.Event.StockPriceChangedEvent;
import pl.slaszu.gpw.stocksource.domain.StockDto;
import pl.slaszu.gpw.stocksource.domain.StockFetchedEvent;

@Component
@Slf4j
public class CreateStockListener {

    @Autowired
    private CreateStockService createStockService;

    @Autowired
    private CacheManager cacheManager;

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

    @EventListener
    @Async
    public void whenStockChanged(StockChangedEvent event) {
        Cache stockCache = this.cacheManager.getCache("stock");
        if (stockCache == null) {
            return;
        }

        stockCache.clear();
        log.debug("Cache for stock cleared");
    }

    @EventListener
    @Async
    public void whenStockPriceChanged(StockPriceChangedEvent event) {
        Cache stockPriceCache = this.cacheManager.getCache("stock_price");
        if (stockPriceCache == null) {
            return;
        }

        String code = event.getStockPrice().getStock().getCode();
        stockPriceCache.evictIfPresent(code.toLowerCase());
        log.debug("Cache for stock_price:%s cleared".formatted(code));
    }
}
