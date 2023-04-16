package pl.slaszu.gpw.stock.application.ListStocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListStocksService {

    @Autowired
    private StockViewModelRepositoryInterface stockViewModelRepositoryInterface;

    public List<StockViewModel> getAllStocks() {
        return this.stockViewModelRepositoryInterface.getAll();
    }

    public List<StockViewModel> getAllStocksLike(String query) {
        return this.stockViewModelRepositoryInterface.getAllLike(query);
    }
}
