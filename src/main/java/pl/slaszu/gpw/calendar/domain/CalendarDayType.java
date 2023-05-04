package pl.slaszu.gpw.calendar.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CalendarDayType {
    DAY_OFF("OFF"); // day without stock quotes

    private String code;

    public String getCode() {
        return this.code;
    }
}
