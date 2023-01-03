package pl.slaszu.gpw.stock.application.ListStocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListStocksService {

    @Autowired
    private StockViewModelRepositoryInterface stockViewModelRepositoryInterface;

    public List<StockViewModel> query(ListStocksQuery command) {
        return this.stockViewModelRepositoryInterface.getAll();
    }
}
