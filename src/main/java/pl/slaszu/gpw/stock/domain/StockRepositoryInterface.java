package pl.slaszu.gpw.stock.domain;

import java.util.List;

public interface StockRepositoryInterface {
    public List<Stock> getAll();

    public void save(Stock stock);
}
