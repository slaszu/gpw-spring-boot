package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.application.FetchStocks.DataProviderInterface;
import pl.slaszu.gpw.stocksource.application.FetchStocks.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.FetchStocks.StockDto;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class TodayDataProvider implements DataProviderInterface {

    private String url;

    public TodayDataProvider(
            @Value("{gpw.gpwpl.url-today}") String url
    ) {
        this.url = url;
    }

    @SneakyThrows
    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {

        log.debug("Gpw.pl today url '%s'".formatted(this.url));

        Workbook workbook = new HSSFWorkbook(new URL(this.url).openStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.rowIterator();

        List<StockDto> result = new ArrayList<>();
        // skip first 2 rows
        iterator.next();
        iterator.next();
        while (iterator.hasNext()) {
            Row row = iterator.next();

            result.add(this.fromRow(row));
        }

        return result;
    }

    private StockDto fromRow(Row row) throws ParseException {
        Date date = new Date();

        return new StockDto(
                "", // code, not exists in gpw.pl
                row.getCell(2).getStringCellValue(), // name
                (float) row.getCell(9).getNumericCellValue(), // open
                (float) row.getCell(11).getNumericCellValue(), // high
                (float) row.getCell(10).getNumericCellValue(), // low
                (float) row.getCell(12).getNumericCellValue(), // price
                (int) row.getCell(23).getNumericCellValue(), // volumen
                (int) row.getCell(24).getNumericCellValue() * 1000, // amount
                date
        );
    }
}
