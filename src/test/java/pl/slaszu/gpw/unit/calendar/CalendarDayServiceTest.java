package pl.slaszu.gpw.unit.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.slaszu.gpw.calendar.CalendarDayService;
import pl.slaszu.gpw.calendar.Config;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalendarDayServiceTest {

    private CalendarDayService calendarDayService;

    @BeforeEach
    public void init() {
        Config config = new Config();
        this.calendarDayService = new CalendarDayService(config.getHolidayManager());
    }

    @Test
    public void testHolidays() {
        List<LocalDate> holidays = Arrays.asList(
            LocalDate.of(2023, 1, 1),
            LocalDate.of(2023, 5, 1),
            LocalDate.of(2023, 5, 3),
            LocalDate.of(2023, 6, 8)
        );

        holidays.forEach(date -> {
            assertTrue(this.calendarDayService.isHoliday(date));
        });

        List<LocalDate> holidaysNo = Arrays.asList(
            LocalDate.of(2023, 1, 2),
            LocalDate.of(2023, 5, 2),
            LocalDate.of(2023, 5, 10)
        );

        holidaysNo.forEach(date -> {
            assertFalse(this.calendarDayService.isHoliday(date));
        });
    }

    @Test
    public void testWeekends() {
        List<LocalDate> weekends = Arrays.asList(
            LocalDate.of(2023, 5, 6),
            LocalDate.of(2023, 5, 7)
        );

        weekends.forEach(date -> {
            assertTrue(this.calendarDayService.isWeekend(date));
        });

        List<LocalDate> weekendsNo = Arrays.asList(
            LocalDate.of(2023, 5, 4),
            LocalDate.of(2023, 5, 5)
        );

        weekendsNo.forEach(date -> {
            assertFalse(this.calendarDayService.isWeekend(date));
        });
    }
}
