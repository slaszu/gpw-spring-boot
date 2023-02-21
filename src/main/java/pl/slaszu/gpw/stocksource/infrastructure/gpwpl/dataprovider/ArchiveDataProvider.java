package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.application.FetchStocks.DataProviderInterface;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ArchiveDataProvider implements DataProviderInterface {

    private String url;

    public ArchiveDataProvider(@Value("${gpw.gpwpl.url-archive}") String url) {
        this.url = url;
    }

    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {
        // prepare url
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String currentUrl = this.url.replace("[date]", format.format(date));

        log.debug("Gpw.pl archive date '%s'".formatted(format.format(date)));
        log.debug("Gpw.pl archive url '%s'".formatted(currentUrl));

        // TODO: 21.02.2023 download file first 
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(currentUrl));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Workbook workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
