package pl.slaszu.gpw.stock.application.ListStocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListStocksService {

    @Autowired
    private StockViewModelRepositoryInterface stockViewModelRepository;

    @Cacheable("stock")
    public List<StockViewModel> getAllStocks() {
        return this.stockViewModelRepository.getAll();
    }

    public List<StockViewModel> getAllStocksLike(String query) {
        return this.getAllStocks().stream()
            .filter(stockViewModel -> {

                String code = stockViewModel.getCode();
                if (code != null && code.toLowerCase().contains(query.toLowerCase())) {
                    return true;
                }
                String name = stockViewModel.getName();
                if (name != null && name.toLowerCase().contains(query.toLowerCase())) {
                    return true;
                }

                return false;
            })
            .toList();
    }
}
