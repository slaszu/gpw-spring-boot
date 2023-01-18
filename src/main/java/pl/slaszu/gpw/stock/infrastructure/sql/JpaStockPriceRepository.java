package pl.slaszu.gpw.stock.infrastructure.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.model.StockPrice;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaStockPriceRepository extends JpaRepository<StockPrice, UUID> {
    public Optional<StockPrice> findByStockAndDate(Stock stock, Date date);

}
