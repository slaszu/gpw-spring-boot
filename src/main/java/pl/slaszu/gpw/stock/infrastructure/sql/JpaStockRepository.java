package pl.slaszu.gpw.stock.infrastructure.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.domain.model.Stock;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaStockRepository extends JpaRepository<Stock, UUID> {
    public Optional<Stock> findByCode(String code);

    public Optional<Stock> findByName(String name);
}