package pl.slaszu.gpw.stocksource.infrastructure.stooq.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeaderRepository extends JpaRepository<Header, Long> {

    public Optional<Header> findByHeaderName(String headerName);

}