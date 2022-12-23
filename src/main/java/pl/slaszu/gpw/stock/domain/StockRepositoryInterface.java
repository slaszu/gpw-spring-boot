package pl.slaszu.gpw.stock.domain;

import java.util.Optional;
import java.util.UUID;

public interface StockRepositoryInterface {
    public Optional<Stock> getById(UUID stockId);

    public void save(Stock stock);
}
