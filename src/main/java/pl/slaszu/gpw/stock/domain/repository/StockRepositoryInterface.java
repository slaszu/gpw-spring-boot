package pl.slaszu.gpw.stock.domain.repository;

import pl.slaszu.gpw.stock.domain.model.Stock;

import java.util.Optional;
import java.util.UUID;

public interface StockRepositoryInterface {
    public Optional<Stock> getById(UUID stockId);

    public void save(Stock stock);
}
