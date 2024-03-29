package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.domain.DataProviderInterface;
import pl.slaszu.gpw.stocksource.domain.exception.FetchStocksException;
import pl.slaszu.gpw.stocksource.domain.StockDto;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TodayDataProvider implements DataProviderInterface {

    private String url;

    private String dirTemp;


    public TodayDataProvider(
        @Value("${gpw.gpwpl.url-today}") String url,
        @Value("${gpw.gpwpl.dir-temp}") String dirTemp
    ) {
        this.url = url;
        this.dirTemp = dirTemp;
    }

    @SneakyThrows
    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {

        log.info("Gpw.pl today url '%s'".formatted(this.url));

        File file = new File(this.dirTemp, "today_latest.xls");
        FileUtils.copyURLToFile(new URL(this.url), file, 3000, 3000);

        Document document = Jsoup.parse(file);

        Elements table_tr = document.select("table tr");

        // missing two rows
        table_tr.remove(0);
        table_tr.remove(0);

        List<StockDto> stockCodes = new ArrayList<>();

        for (Element row : table_tr) {
            // find td elements in row
            Elements cells = row.select("td");

            if (cells.get(2).text().isEmpty()) {
                break;
            }

            stockCodes.add(
                this.fromTableCells(cells)
            );
        }

        return stockCodes;
    }

    private StockDto fromTableCells(Elements cells) {
        Date date = new Date();
        return new StockDto(
            cells.get(4).text(), // code
            cells.get(2).text(), // name
            this.fromStringToFloat(cells.get(9).text()), // open
            this.fromStringToFloat(cells.get(11).text()), // high
            this.fromStringToFloat(cells.get(10).text()), // low
            this.fromStringToFloat(cells.get(12).text()), // price
            this.fromStringToFloat(cells.get(23).text()).intValue(), // volumen
            (int) (this.fromStringToFloat(cells.get(24).text()) * 1000), // amount
            date
        );
    }

    private Float fromStringToFloat(String string) {
        String stringX = string.replace(",", ".").replaceAll("[^0-9.]", "");
        if (stringX.isEmpty()) {
            return (float) 0;
        }

        return Float.valueOf(stringX);
    }
}
