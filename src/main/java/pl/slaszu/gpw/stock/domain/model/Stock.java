package pl.slaszu.gpw.stock.domain.model;

import jakarta.persistence.*;
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

    @Column(unique = true)
    private String code;

    private String name;

    public Stock() {
    }
}