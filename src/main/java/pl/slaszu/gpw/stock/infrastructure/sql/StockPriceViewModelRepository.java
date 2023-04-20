package pl.slaszu.gpw.stock.infrastructure.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.slaszu.gpw.stock.application.ListStockPrice.StockPriceViewModel;
import pl.slaszu.gpw.stock.application.ListStockPrice.StockPriceViewModelRepositoryInterface;

import java.util.List;

@Repository
public class StockPriceViewModelRepository implements StockPriceViewModelRepositoryInterface {

    @Autowired
    private JpaStockPriceRepository jpaStockPriceRepository;

    @Override
    public List<StockPriceViewModel> getAllByStockCode(String code) {

        return this.jpaStockPriceRepository.findAllByStockCode(code).stream().map(StockPriceViewModel::fromStockPrice).toList();

    }
}
