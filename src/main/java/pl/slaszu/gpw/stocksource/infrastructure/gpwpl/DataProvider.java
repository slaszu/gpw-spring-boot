package pl.slaszu.gpw.stocksource.infrastructure.gpwpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.calendar.CalendarDayService;
import pl.slaszu.gpw.stocksource.application.DataProviderInterface;
import pl.slaszu.gpw.stocksource.application.FetchStocksException;
import pl.slaszu.gpw.stocksource.application.StockDto;
import pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider.ArchiveDataProvider;
import pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider.TodayDataProvider;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class DataProvider implements DataProviderInterface {

    @Autowired
    private ArchiveDataProvider archiveDataProvider;

    @Autowired
    private TodayDataProvider todayDataProvider;

    @Autowired
    private CalendarDayService calendarDayService;

    @Override
    public List<StockDto> getData(Date date) throws FetchStocksException {

        LocalDate localDate = this.convertToLocalDateViaInstant(date);
        if (this.calendarDayService.isHoliday(localDate)) {
            throw new FetchStocksException("Date %s is holiday !".formatted(localDate.toString()));
        }

        if (this.calendarDayService.isWeekend(localDate)) {
            throw new FetchStocksException("Date %s is weekend !".formatted(localDate.toString()));
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        if (date.getTime() > now.getTime()) {
            throw new FetchStocksException("Date must by equal today or earlier, you given date is later than today !");
        }

        String givenS = format.format(date);
        String nowS = format.format(now);

        if (givenS.equals(nowS)) {
            return this.todayDataProvider.getData(date);
        }

        return this.archiveDataProvider.getData(date);
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }
}
