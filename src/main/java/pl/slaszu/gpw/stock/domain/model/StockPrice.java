package pl.slaszu.gpw.stock.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "stock_price", indexes = {
    @Index(name = "idx_stock_price_date", columnList = "date")
})
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    public StockPrice() {
    }

    public StockPrice(UUID id, Stock stock) {
        this.id = id;
        this.stock = stock;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }

    @PrePersist
    public void prePersist() {
        this.updatedAt = new Date();
    }
}