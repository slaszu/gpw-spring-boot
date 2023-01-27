package pl.slaszu.gpw.stock.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "stock_price")
@AllArgsConstructor
@Data
public class StockPrice {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private Float priceOpen;

    private Float priceHigh;

    private Float priceLow;

    private Float price;

    private Integer volume;

    private Integer amount;

    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    public StockPrice() {
    }

    public StockPrice(UUID id, Stock stock) {
        this.id = id;
        this.stock = stock;
    }
}