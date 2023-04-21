package pl.slaszu.gpw.stock.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "stock")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Stock {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(unique = true, length = 10)
    private String code;

    @Column(unique = true, length = 50)
    private String name;

    public Stock() {
    }
}