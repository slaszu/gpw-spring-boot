package pl.slaszu.gpw.calendar.infrastructure.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.calendar.domain.CalendarDay;
import pl.slaszu.gpw.calendar.domain.CalendarDayType;

import java.util.List;

@Repository
public interface JpaCalendarDayRepository extends JpaRepository<CalendarDay, Long> {

    public List<CalendarDay> findAllByType(CalendarDayType type);
}
