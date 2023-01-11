package pl.slaszu.gpw.stocksource.infrastructure.stooq.model;

import jakarta.persistence.*;

// TODO: 11.01.2023 entity must keep cookie for stooq website
@Entity
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
