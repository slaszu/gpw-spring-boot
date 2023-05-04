package pl.slaszu.gpw.calendar.infrastructure.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.calendar.domain.CalendarDay;
import pl.slaszu.gpw.calendar.domain.CalendarDayRepositoryInterface;
import pl.slaszu.gpw.calendar.domain.CalendarDayType;

import java.util.List;

@Repository
public class CalendarDayRepository implements CalendarDayRepositoryInterface {

    @Autowired
    private JpaCalendarDayRepository calendarDayRepository;

    @Override
    public List<CalendarDay> getAllByType(CalendarDayType type) {

        return this.calendarDayRepository.findAllByType(type);
    }
}
