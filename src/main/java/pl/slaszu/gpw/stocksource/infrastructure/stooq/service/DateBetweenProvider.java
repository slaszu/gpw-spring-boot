package pl.slaszu.gpw.stocksource.infrastructure.stooq.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DateBetweenProvider {
    private Long dayMilliseconds = (long) (1000 * 60 * 60 * 24);

    public List<Date> getRangeBetween(Date from, Date to) throws Exception {
        List<Date> result = new ArrayList<>();

        if (from.getTime() > to.getTime()) {
            throw new Exception("Date \"from\" %s must be earlier then date \"to\" %s".formatted(from.toString(), to.toString()));
        }

        while (from.getTime() <= to.getTime()) {
            result.add(from);
            from = new Date(from.getTime() + this.dayMilliseconds);
        }

        return result;
    }

    public List<Date> getRangeBetween(Date from) throws Exception {
        return this.getRangeBetween(from, new Date());
    }
}
