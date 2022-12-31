package pl.slaszu.gpw.stock.domain.repository;

import pl.slaszu.gpw.stock.domain.model.StockViewModel;

import java.util.List;

public interface StockViewModelRepositoryInterface {

    public List<StockViewModel> getAll();
}
