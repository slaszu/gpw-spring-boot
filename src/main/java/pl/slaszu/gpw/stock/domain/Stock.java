package pl.slaszu.gpw.stock.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String code;

    public Stock() {
    }

    public Stock(Long id, String code) {
        this.id = id;
        this.code = code;
    }


    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}