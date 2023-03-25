package pl.slaszu.gpw.stock.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;

    public Stock() {
    }
}