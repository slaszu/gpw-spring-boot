package pl.slaszu.gpw.calendar.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "calendar_day")
@Getter
@NoArgsConstructor
public class CalendarDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate localDate;

    private CalendarDayType type;

    public CalendarDay(LocalDate localDate, CalendarDayType type) {
        this.localDate = localDate;
        this.type = type;
    }
}