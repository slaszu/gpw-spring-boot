package pl.slaszu.gpw.stocksource.infrastructure.stooq.service;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDto;

import java.util.Date;
import java.util.List;

@Service
public class StockDtoProvider {
    public StockDto fromTableCells(List<Element> cells, Date date) {
        return new StockDto(
                cells.get(0).text(),
                cells.get(1).text(),
                this.fromStringToFloat(cells.get(2).text()),
                this.fromStringToFloat(cells.get(3).text()),
                this.fromStringToFloat(cells.get(4).text()),
                this.fromStringToFloat(cells.get(5).text()),
                this.fromStringToInt(cells.get(7).text()),
                this.fromStringToInt(cells.get(8).text()),
                date
        );
    }

    private Float fromStringToFloat(String string) {
        if (string.isEmpty()) {
            return (float) 0;
        }

        return Float.valueOf(string);
    }

    private Integer fromStringToInt(String string) {
        if (string.isEmpty()) {
            return 0;
        }

        Float v = Float.valueOf(string
                .replace("k", "")
                .replace("m", "")
        );

        if (string.indexOf("k") > 0) {
            v = v * 1000;
        }
        if (string.indexOf("m") > 0) {
            v = v * 1000;
        }

        return v.intValue();
    }
}
