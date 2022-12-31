package pl.slaszu.gpw.stock.infrastructure.source;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.slaszu.gpw.stock.domain.source.ProviderInterface;
import pl.slaszu.gpw.stock.domain.source.StockData;

import java.io.IOException;
import java.util.List;

public class StooqProvider implements ProviderInterface {

    private String BASE_URL = "https://stooq.pl/t/?i=523&v=0&l=[pageNumber]";

    @Override
    public Iterable<StockData> getAll() {
        return null;
    }

    private List<StockData> getDataFromPageNumber(Integer pageNumber) throws IOException {

        String url = this.BASE_URL.replaceFirst("[pageNumber]", pageNumber.toString());


        Document doc = Jsoup.connect(url).get();

        return null;
    }
}
