package pl.slaszu.gpw.stock.infrastructure.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.repository.StockRepositoryInterface;

import java.util.Optional;
import java.util.UUID;

@Repository
public class StockRepository implements StockRepositoryInterface {

    @Autowired
    private JpaStockRepository jpaStockRepository;

    @Override
    public Optional<Stock> getById(UUID stockId) {
        return this.jpaStockRepository.findById(stockId);
    }

    @Override
    public Optional<Stock> getByCode(String code) {
        return this.jpaStockRepository.findByCode(code);
    }

    @Override
    public Optional<Stock> getByName(String name) {
        return this.jpaStockRepository.findByName(name);
    }

    @Override
    public Stock save(Stock stock) {
        return this.jpaStockRepository.save(stock);
    }
}
