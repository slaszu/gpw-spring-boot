package pl.slaszu.gpw.stock.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.domain.Stock;
import pl.slaszu.gpw.stock.domain.StockRepositoryInterface;

import java.util.List;

@Repository
public class StockRepository implements StockRepositoryInterface {

    @Autowired
    private JpaStockRepository jpaStockRepository;

    @Override
    public List<Stock> getAll() {
        return this.jpaStockRepository.findAll();
    }

    @Override
    public void save(Stock stock) {
        this.jpaStockRepository.save(stock);
    }
}
