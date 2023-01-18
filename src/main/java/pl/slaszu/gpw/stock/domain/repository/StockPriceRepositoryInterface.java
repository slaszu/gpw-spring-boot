package pl.slaszu.gpw.stock.domain.repository;

import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.model.StockPrice;

import java.util.Date;
import java.util.Optional;

public interface StockPriceRepositoryInterface {
    public Optional<StockPrice> getByStockAndDate(Stock stock, Date date);

    public void save(StockPrice stockPrice);
}
