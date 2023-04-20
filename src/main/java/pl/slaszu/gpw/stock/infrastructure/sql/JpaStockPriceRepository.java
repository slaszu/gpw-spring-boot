package pl.slaszu.gpw.stock.infrastructure.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.model.StockPrice;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaStockPriceRepository extends JpaRepository<StockPrice, UUID> {
    public Optional<StockPrice> findByStockAndDate(Stock stock, Date date);

    // TODO: 20.04.2023 sortowanie po dacie asc, desc i limit podawany w parametrze endpointu 
    @Query("select sp from StockPrice sp join sp.stock s where s.code = ?1 order by sp.date")
    public List<StockPrice> findAllByStockCode(String stockCode);

}
