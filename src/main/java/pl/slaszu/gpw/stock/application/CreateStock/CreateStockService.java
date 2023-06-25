package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slaszu.gpw.sharedkernel.domain.EventDispatcherInterface;
import pl.slaszu.gpw.stock.application.CreateStock.Event.StockChangedEvent;
import pl.slaszu.gpw.stock.application.CreateStock.Event.StockPriceChangedEvent;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.model.StockPrice;
import pl.slaszu.gpw.stock.domain.repository.StockPriceRepositoryInterface;
import pl.slaszu.gpw.stock.domain.repository.StockRepositoryInterface;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CreateStockService {

    private StockRepositoryInterface stockRepository;

    private StockPriceRepositoryInterface stockPriceRepository;

    private EventDispatcherInterface eventDispatcher;

    @Transactional
    public void create(CreateStockCommand command) {

        // stock
        Stock stock = this.getOrCreateStock(command);
        Stock stockSaved = this.stockRepository.save(stock);

        if (!stockSaved.equals(stock)) {
            this.eventDispatcher.dispatch(new StockChangedEvent(stockSaved));
        }

        CreateStockPriceCommand createStockPriceCommand = command.getCreateStockPriceCommand();
        if (createStockPriceCommand != null) {
            // stock price
            StockPrice stockPrice = this.getOrCreateStockPrice(stockSaved, createStockPriceCommand.getDate());
            this.refreshStockPrice(stockPrice, createStockPriceCommand);

            StockPrice stockPriceSaved = this.stockPriceRepository.save(stockPrice);

            if (!stockPriceSaved.equals(stockPrice)) {
                this.eventDispatcher.dispatch(new StockPriceChangedEvent(stockPriceSaved));
            }
        }
    }

    private void refreshStockPrice(StockPrice stockPrice, CreateStockPriceCommand createStockPriceCommand) {
        stockPrice.setPrice(createStockPriceCommand.getPrice());
        stockPrice.setPriceLow(createStockPriceCommand.getPriceLow());
        stockPrice.setPriceHigh(createStockPriceCommand.getPriceHigh());
        stockPrice.setPriceOpen(createStockPriceCommand.getPriceOpen());
        stockPrice.setDate(createStockPriceCommand.getDate());
        stockPrice.setAmount(createStockPriceCommand.getAmount());
        stockPrice.setVolume(createStockPriceCommand.getVolume());
    }

    private StockPrice getOrCreateStockPrice(Stock stock, Date date) {
        Optional<StockPrice> byStockAndDate = this.stockPriceRepository.getByStockAndDate(stock, date);
        if (byStockAndDate.isPresent()) {
            return byStockAndDate.get();
        }

        UUID uuid = UUID.randomUUID();
        return new StockPrice(
            uuid,
            stock
        );
    }

    private Stock getOrCreateStock(CreateStockCommand command) {

        String code = command.getCode();
        String name = command.getName();
        Optional<Stock> byCode;
        Optional<Stock> byName;

        /*
        1. if code exists, search for stock by code
        2. if stock by code exists then finish searching
        3. if name exists, search for stock by name
        4. if stock by name exists then finish searching
        5. else create new stock entity
         */

        // TODO: 25/03/2023 write unit tests to this bisnes logic 

        if (!code.equals("")) {
            byCode = this.stockRepository.getByCode(command.getCode());
            if (byCode.isPresent()) {
                return CreateStockFactory.fillIfNeeded(byCode.get(), code, name);
            }
        }

        if (!name.equals("")) {
            byName = this.stockRepository.getByName(command.getName());
            if (byName.isPresent()) {
                return CreateStockFactory.fillIfNeeded(byName.get(), code, name);
            }
        }

        Stock stock = new Stock(UUID.randomUUID(), code, name);
        return CreateStockFactory.fillIfNeeded(stock);
    }
}
