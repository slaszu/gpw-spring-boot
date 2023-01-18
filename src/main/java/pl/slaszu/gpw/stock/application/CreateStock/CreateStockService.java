package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.model.StockPrice;
import pl.slaszu.gpw.stock.domain.repository.StockPriceRepositoryInterface;
import pl.slaszu.gpw.stock.domain.repository.StockRepositoryInterface;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateStockService {
    @Autowired
    private StockRepositoryInterface stockRepository;

    private StockPriceRepositoryInterface stockPriceRepository;

    public void create(CreateStockCommand command) {

        Stock stock = this.getOrCreateStock(command);

        CreateStockPriceCommand createStockPriceCommand = command.getCreateStockPriceCommand();
        if (createStockPriceCommand == null) {
            return;
        }

        StockPrice stockPrice = this.getOrCreateStockPrice(stock, createStockPriceCommand.getDate());
        this.refreshStockPrice(stockPrice, createStockPriceCommand);

        // TODO: 18.01.2023 to cos nie trybi z zapisem jednoczesnie stock a potem stockPrice powiazanego ze stock 
        this.stockRepository.save(stock);
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
            return byStockAndDate.get();
        }

        UUID uuid = UUID.randomUUID();

        return new StockPrice(
                uuid,
                stock
        );
    }

    private Stock getOrCreateStock(CreateStockCommand command) {
        Optional<Stock> byCode = this.stockRepository.getByCode(command.getCode());
        if (byCode.isPresent()) {
            return byCode.get();
        }

        UUID uuid = UUID.randomUUID();

        return new Stock(uuid, command.getCode(), command.getName());
    }
}
