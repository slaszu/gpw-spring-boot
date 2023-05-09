package pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.domain.DataProviderInterface;
import pl.slaszu.gpw.stocksource.domain.StockDto;
import pl.slaszu.gpw.stocksource.domain.exception.FetchStocksException;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ArchiveDataProvider implements DataProviderInterface {

    private String url;

    public ArchiveDataProvider(
        @Value("${gpw.gpwpl.url-archive}") String url
    ) {
        this.url = url;
    }

    @SneakyThrows
    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {
        // prepare url
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String currentUrl = this.url.replace("[date]", format.format(date));

        log.info("Gpw.pl archive url '%s'".formatted(currentUrl));

        Workbook workbook = new HSSFWorkbook(new URL(currentUrl).openStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.rowIterator();

        List<StockDto> result = new ArrayList<>();
        // skip first row
        iterator.next();
        while (iterator.hasNext()) {
            Row row = iterator.next();

            result.add(this.fromRow(row));
        }


        return result;
    }

    private StockDto fromRow(Row row) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = formatter.parse(row.getCell(0).getStringCellValue());

        return new StockDto(
            "", // code, not exists in gpw.pl
            row.getCell(1).getStringCellValue(), // name
            (float) row.getCell(4).getNumericCellValue(), // open
            (float) row.getCell(5).getNumericCellValue(), // high
            (float) row.getCell(6).getNumericCellValue(), // low
            (float) row.getCell(7).getNumericCellValue(), // price
            (int) row.getCell(9).getNumericCellValue(), // volumen
            (int) (row.getCell(11).getNumericCellValue() * 1000), // amount
            date
        );
    }


}
