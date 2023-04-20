package pl.slaszu.gpw.stock.application.ListStocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListStocksService {

    @Autowired
    private StockViewModelRepositoryInterface stockViewModelRepository;

    public List<StockViewModel> getAllStocks() {
        return this.stockViewModelRepository.getAll();
    }

    public List<StockViewModel> getAllStocksLike(String query) {
        return this.stockViewModelRepository.getAllLike(query);
    }
}
