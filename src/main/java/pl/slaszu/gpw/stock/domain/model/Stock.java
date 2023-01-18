package pl.slaszu.gpw.stock.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "stock")
@Getter
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;

    private String name;

    public Stock() {
    }
}