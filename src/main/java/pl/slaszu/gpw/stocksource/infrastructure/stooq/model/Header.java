package pl.slaszu.gpw.stocksource.infrastructure.stooq.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO: 11.01.2023 entity must keep cookie for stooq website
@Entity
@Getter
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

    private String headerName;

    @Column(length = 5000)
    private String headerValue;
}
