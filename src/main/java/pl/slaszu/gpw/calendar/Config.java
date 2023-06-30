package pl.slaszu.gpw.calendar;

import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public HolidayManager getHolidayManager() {
        return HolidayManager.getInstance(ManagerParameters.create("pl"));
    }
}
