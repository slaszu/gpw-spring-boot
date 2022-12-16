package pl.slaszu.gpw.stock.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_price")
public class StockPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer price;

    public StockPrice() {
    }

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public StockPrice(Integer price, Stock stock) {
        this.price = price;
        this.stock = stock;
    }
}