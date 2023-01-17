package pl.slaszu.gpw.stocksource.infrastructure.stooq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.Header;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderRepository;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModel;

import java.util.Optional;

@Service
public class HeaderManager {

    @Autowired
    private HeaderRepository headerRepository;

    public void createOrUpdate(HeaderViewModel headerViewModel) {
        Optional<Header> byHeaderName = this.headerRepository.findByHeaderName(headerViewModel.getName());

        Header header;
        if (byHeaderName.isPresent()) {
            header = byHeaderName.get();
            header.setHeaderValue(headerViewModel.getValue());
        } else {
            header = new Header(headerViewModel.getName(), headerViewModel.getValue());
        }

        this.headerRepository.save(header);
    }

    public void delete(String headerName) {
        Optional<Header> byHeaderName = this.headerRepository.findByHeaderName(headerName);
        byHeaderName.ifPresent(this.headerRepository::delete);
    }
}
