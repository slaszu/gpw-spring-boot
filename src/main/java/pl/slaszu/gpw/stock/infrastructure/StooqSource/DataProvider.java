package pl.slaszu.gpw.stock.infrastructure.StooqSource;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stock.application.FetchStocks.DataProviderInterface;
import pl.slaszu.gpw.stock.application.FetchStocks.StockDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DataProvider implements DataProviderInterface {
    /**
     * eg. https://stooq.pl/t/?i=513&v=1&l=[pageNumber]
     */
    private String urlWithPlaceholders;

    @Override
    public List<StockDTO> getData() {

        try {
            Document doc = Jsoup.connect(this.getUrlForPageNumber(1)).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<>();
    }

    private String getUrlForPageNumber(Integer pageNumber) {
        return this.urlWithPlaceholders.replace("[pageNumber]", pageNumber.toString());
    }
}
