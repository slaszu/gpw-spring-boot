package pl.slaszu.gpw.stock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;


    public Stock() {
    }

    public Stock(String code) {
        this.code = code;
    }

    public UUID getId() {
        return this.id;
    }

    public String getCode() {
        return code;
    }
}