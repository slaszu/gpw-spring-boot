package pl.slaszu.gpw.stock.infrastructure.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.domain.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaStockRepository extends JpaRepository<Stock, UUID> {
    public Optional<Stock> findByCode(String code);

    public Optional<Stock> findByName(String name);

    @Query("select s from Stock s where s.code LIKE %?1% or s.name LIKE %?1%")
    public List<Stock> findAll(String query);
}