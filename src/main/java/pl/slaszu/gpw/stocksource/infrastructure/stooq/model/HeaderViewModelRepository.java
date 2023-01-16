package pl.slaszu.gpw.stocksource.infrastructure.stooq.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HeaderViewModelRepository {

    @Autowired
    private HeaderRepository headerRepository;

    public List<HeaderViewModel> getAll() {
        return this.headerRepository.findAll().stream().map(
                (Header h) -> new HeaderViewModel(h.getHeaderName(), h.getHeaderValue())
        ).toList();
    }

    public void create(HeaderViewModel headerViewModel) {
        this.headerRepository.save(new Header(headerViewModel.getName(), headerViewModel.getValue()));
    }
}
