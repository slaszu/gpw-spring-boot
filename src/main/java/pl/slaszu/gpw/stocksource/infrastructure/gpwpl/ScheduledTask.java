package pl.slaszu.gpw.stocksource.infrastructure.gpwpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.slaszu.gpw.stocksource.domain.exception.FetchStocksException;
import pl.slaszu.gpw.stocksource.domain.FetchStocksService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private DataProvider gpwplDataProvider;

    @Autowired
    private FetchStocksService fetchStocksService;

    /*
      @see https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronExpression.html
     * in cron statement is 6 digits
     */
    @Scheduled(cron = "0 */15 9-17 * * 1-5")
    public void getCurrentStock() {
        Date date = new Date();

        log.info("Scheduler {}", dateFormat.format(date));
        try {
            this.fetchStocksService.fetch(this.gpwplDataProvider, date);
        } catch (FetchStocksException e) {
            log.info("FetchStocksException: %s".formatted(e.getMessage()));
        }
    }
}
