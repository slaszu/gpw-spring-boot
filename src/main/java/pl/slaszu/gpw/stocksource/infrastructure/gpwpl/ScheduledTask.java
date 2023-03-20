package pl.slaszu.gpw.stocksource.infrastructure.gpwpl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.slaszu.gpw.stocksource.infrastructure.gpwpl.dataprovider.TodayDataProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private TodayDataProvider todayDataProvider;

    @SneakyThrows
    @Scheduled(cron = "0 */15 10-17 * * 0-5 ")
    @Scheduled(cron = "0 15,30,40 9 * * 0-5")
    public void getCurrentStock() {
        Date date = new Date();

        log.info("Scheduler {}", dateFormat.format(date));
        todayDataProvider.getData(date);
    }
}
