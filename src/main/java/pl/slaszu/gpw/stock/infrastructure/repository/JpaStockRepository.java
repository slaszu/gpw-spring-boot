package pl.slaszu.gpw.stock.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.domain.Stock;

import java.util.UUID;

@Repository
public interface JpaStockRepository extends JpaRepository<Stock, UUID> {
}