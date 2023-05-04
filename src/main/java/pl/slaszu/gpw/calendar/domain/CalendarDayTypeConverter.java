package pl.slaszu.gpw.calendar.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CalendarDayTypeConverter implements AttributeConverter<CalendarDayType, String> {

    @Override
    public String convertToDatabaseColumn(CalendarDayType type) {
        if (type == null) {
            return null;
        }
        return type.getCode();
    }

    @Override
    public CalendarDayType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(CalendarDayType.values())
            .filter(c -> c.getCode().equals(code))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
