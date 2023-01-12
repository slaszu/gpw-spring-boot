package pl.slaszu.gpw.stocksource.infrastructure.stooq;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.application.FetchStocks.DataProviderInterface;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataProvider implements DataProviderInterface {

    private String urlWithPlaceholders;

    public DataProvider(@Value("${gpw.stooq.url}") String urlWithPlaceholders) {
        this.urlWithPlaceholders = urlWithPlaceholders;
    }

    @Override
    public List<StockDTO> getData() throws FetchStocksException {

        ArrayList<StockDTO> stockCodes = new ArrayList<>();
        int pageNumber = 1;
        int counter = 0;

        do {
            String url = this.getUrlForPageNumber(pageNumber++);
            Document doc = this.getDocumentForUrl(url);
            Elements rows = doc.select("#fth1 tr");

            if (rows.isEmpty() && stockCodes.isEmpty()) {
                log.error(doc.body().toString());
                throw new FetchStocksException("Stooq does not return any data. Set new cookie and try again");
            }

            counter = 0;
            for (Element row : rows) {
                // find td elements in row
                Elements cells = row.select("td");
                // if not exists (header of table) continue
                if (cells.isEmpty()) {
                    continue;
                }

                stockCodes.add(
                        new StockDTO(cells.get(0).text())
                );
                counter++;
            }

            log.debug(stockCodes.toString());

        } while (counter > 0);

        return stockCodes;
    }

    private Document getDocumentForUrl(String url) throws FetchStocksException {

        log.info(url);
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept-Language", "pl,en-US;q=0.9,en;q=0.8");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.put("Cookie",

                "FCCDCF=[null,null,null,[\"CPkB-MAPkB-MAEsABBPLCuCoAP_AAG_AAB5YHQpB7D7FbSFCyP55aLsAMAhXRkCAQqQAAASBAmABQAKQIAQCkkAQFASgBAACAAAgIAJBAQIMCAgACUABQAAAAAEEAAAABAAIIAAAgAEAAAAIAAACAIAAEAAIAAAAEAAAmQhAAIIACAAABAAAAAAAAAAAAAAAAgdCgHsLsVtIUJI_GkoswAgCFdGQIBCoAAAAIECQAAAApAgBAKQQBAABKAEAAAAACAgAgEBAAgACAABQAFAAAAAAAAAAAAAAAggAACAAQAAAAAAAAIAgAAQAAgAAAAAAACJCEAAggAIAAAAAAAAAAAAAAAAAAACAAA.f-gAAAAAAAA\",\"1~2072.70.89.93.108.122.149.2202.162.167.196.2253.241.2299.259.2357.311.317.323.2373.338.358.2415.415.449.2506.2526.482.486.494.495.2568.2571.2575.540.574.2624.624.2677.817.827.864.981.1048.1051.1095.1097.1127.1171.1201.1205.1211.1276.1301.1365.1415.1449.1570.1577.1651.1716.1753.1765.1870.1878.1889.1958.2012\",\"0E026F94-AECB-420F-B52B-EB5E56B9204E\"],null,null,[]]; FCNEC=[[\"AKsRol-f2vqNLOQM7G4mp3aOkrvLB_X0yjLOB7UUcEup8zxIQU-gBKfWU_Vzto4cJP42cHJnGrsRe_3PI_YIxwtvHZ7URqxLwLKhJl-wAc-CHbxT4TlTa6a044JtOfzL28N9ixNEtp_5wn2IOww8bd3IXV7GDjRbKg==\"],null,[]]; privacy=1671090755; uid=plmh8a86n7o20rp4u44k16saeh; _ga=GA1.2.584589028.1671090755; adblock_d394Nsw9An2fM04s=1; cookie_ta=i~d&t~c&a~lg&z~116&l~0&d~1&ch~0&f~0&lt~57&r~3&o~1; cookie_user=?0005mllg000011400d1300e3|act+pko+co2.e+11b+kgh+asb~0001s2lnmj010e90ej; PHPSESSID=n7gg99j22qe2bmg13sbdvc2nu3; cookie_uu=230112000; _gid=GA1.2.686126939.1673527892; _gat_gtag_UA_64441802_2=1"

                );

        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36")
                    .followRedirects(true)
                    .headers(headers)
                    .timeout(10000)
                    .get();
        } catch (IOException e) {
            throw new FetchStocksException("Page %s cant be fatched".formatted(url), e);
        }

    }

    private String getUrlForPageNumber(Integer pageNumber) {
        return this.urlWithPlaceholders.replace("[page]", pageNumber.toString());
    }
}
