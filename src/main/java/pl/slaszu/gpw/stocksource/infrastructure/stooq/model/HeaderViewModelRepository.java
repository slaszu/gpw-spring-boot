package pl.slaszu.gpw.stocksource.infrastructure.stooq.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HeaderViewModelRepository {

    @Autowired
    private HeaderRepository headerRepository;

    public List<HeaderViewModel> getAll() {
        return this.headerRepository.findAll().stream().map(this::toViewModel).toList();
    }

    private HeaderViewModel toViewModel(Header header) {
        return new HeaderViewModel(header.getHeaderName(), header.getHeaderValue());
    }
}
