package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private StockRepositoryInterface stockRepository;

    private StockPriceRepositoryInterface stockPriceRepository;

    @Transactional
    public void create(CreateStockCommand command) {

        CreateStockPriceCommand createStockPriceCommand = command.getCreateStockPriceCommand();
        if (createStockPriceCommand != null) {
            this.createStockWithPrice(command);
            return;
        }

        Stock stock = this.getOrCreateStock(command);
        this.stockRepository.save(stock);
    }

    private void createStockWithPrice(CreateStockCommand command)
    {
        Stock stock = this.getOrCreateStock(command);

        CreateStockPriceCommand createStockPriceCommand = command.getCreateStockPriceCommand();
        StockPrice stockPrice = this.getOrCreateStockPrice(stock, createStockPriceCommand.getDate());
        this.refreshStockPrice(stockPrice, createStockPriceCommand);

        this.stockPriceRepository.save(stockPrice);
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
            log.debug("getOrCreateStockPrice = get");
            return byStockAndDate.get();
        }

        UUID uuid = UUID.randomUUID();
        log.debug("getOrCreateStockPrice = create");
        return new StockPrice(
                uuid,
                stock
        );
    }

    private Stock getOrCreateStock(CreateStockCommand command) {
        Optional<Stock> byCode = this.stockRepository.getByCode(command.getCode());
        if (byCode.isPresent()) {
            log.debug("getOrCreateStock = get");
            return byCode.get();
        }

        UUID uuid = UUID.randomUUID();
        log.debug("getOrCreateStock = create");
        return new Stock(uuid, command.getCode(), command.getName());
    }
}
