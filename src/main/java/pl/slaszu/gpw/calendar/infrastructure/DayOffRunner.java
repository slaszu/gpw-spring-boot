package pl.slaszu.gpw.calendar.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.slaszu.gpw.calendar.domain.CalendarDay;
import pl.slaszu.gpw.calendar.domain.CalendarDayType;
import pl.slaszu.gpw.calendar.infrastructure.sql.JpaCalendarDayRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class DayOffRunner implements ApplicationRunner {

    @Autowired
    private JpaCalendarDayRepository calendarDayRepository;

    private List<String> yearly = Arrays.asList(
        "2023-06-08",
        "2023-08-15",
        "2023-11-01",
        "2023-12-25",
        "2023-12-26",
        "2024-01-01",
        "2024-04-01",
        "2024-05-01",
        "2024-05-03",
        "2024-05-30",
        "2024-11-01",
        "2024-12-25",
        "2024-12-26"
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LocalDate now = LocalDate.now();

        List<CalendarDay> dayList = this.calendarDayRepository.findAllByType(CalendarDayType.DAY_OFF);

        this.yearly.forEach(s -> {
            LocalDate date = LocalDate.parse(s);
            if (date.isAfter(now)) {
                Optional<CalendarDay> first = dayList.stream().filter(calendarDay -> calendarDay.getLocalDate().equals(date)).findFirst();
                if (first.isEmpty()) {
                    // add to database
                    this.calendarDayRepository.save(new CalendarDay(date, CalendarDayType.DAY_OFF));
                    log.info("DAY_OFF %s has been added to db".formatted(date.toString()));
                }
            }
        });

    }
}
