package pl.slaszu.gpw.stock.application.ListStocks;

import pl.slaszu.gpw.stock.application.ListStocks.StockViewModel;

import java.util.List;

public interface StockViewModelRepositoryInterface {

    public List<StockViewModel> getAll();

    public List<StockViewModel> getAllLike(String like);
}
