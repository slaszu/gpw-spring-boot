package pl.slaszu.gpw.calendar.domain;

import java.util.List;

public interface CalendarDayRepositoryInterface {

    public List<CalendarDay> getAllByType(CalendarDayType type);
}
