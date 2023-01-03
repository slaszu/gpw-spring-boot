package pl.slaszu.gpw.stock.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.application.ListStocks.StockViewModel;
import pl.slaszu.gpw.stock.application.ListStocks.StockViewModelRepositoryInterface;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StockVewModelRepository implements StockViewModelRepositoryInterface {
    @Autowired
    private JpaStockRepository jpaStockRepository;

    @Override
    public List<StockViewModel> getAll() {
        List<Stock> stockList = this.jpaStockRepository.findAll();

        return this.convertStockToStockViewModel(stockList);
    }

    private List<StockViewModel> convertStockToStockViewModel(List<Stock> stockList) {
        return stockList.stream().map(stock ->
                new StockViewModel(
                        stock.getId().toString(),
                        stock.getCode()
                )
        ).collect(Collectors.toList());
    }
}
