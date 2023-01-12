package pl.slaszu.gpw.stocksource.infrastructure.stooq.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderRepository;

import java.util.Map;

@Service
@AllArgsConstructor
public class HeaderProvider {

    @Autowired
    private HeaderRepository repository;

//    public Map<String,String> getHeaders() {
//
//    }
}
