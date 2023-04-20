package pl.slaszu.gpw.stock.application.ListStockPrice;

import java.util.List;

public interface StockPriceViewModelRepositoryInterface {

    public List<StockPriceViewModel> getAllByStockCode(String code);
}
