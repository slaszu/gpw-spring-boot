package pl.slaszu.gpw.stocksource.infrastructure.stooq.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO: 11.01.2023 entity must keep cookie for stooq website
@Entity
@Data
@NoArgsConstructor
public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Header(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    @Column(unique = true)
    private String headerName;

    @Column(length = 5000)
    private String headerValue;
}
