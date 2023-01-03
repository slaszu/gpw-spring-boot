package pl.slaszu.gpw.stock.infrastructure.repository;

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
    public void save(Stock stock) {
        this.jpaStockRepository.save(stock);
    }
}
