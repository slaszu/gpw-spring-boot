package pl.slaszu.gpw.calendar;

import de.focus_shift.HolidayManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CalendarDayService {

    @Autowired
    private HolidayManager holidayManager;

    public boolean isHoliday(LocalDate date) {
        return this.holidayManager.isHoliday(date, "pl");
    }

    public boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY));
    }
}
