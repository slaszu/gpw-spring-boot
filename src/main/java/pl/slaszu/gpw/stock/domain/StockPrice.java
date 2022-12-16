package pl.slaszu.gpw.stock.domain;

import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Entity
@Table(name = "stock_price")
public class StockPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer priceOpen;

    private Integer priceHigh;

    private Integer priceLow;

    private Integer volume;

    private Date date;

    public StockPrice() {
    }

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    public Integer getPriceLow() {
        return priceLow;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

}