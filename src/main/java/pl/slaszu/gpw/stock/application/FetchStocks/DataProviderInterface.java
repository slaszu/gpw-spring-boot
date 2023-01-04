package pl.slaszu.gpw.stock.application.FetchStocks;

import java.util.List;

public interface DataProviderInterface {
    public List<StockDTO> getData();
}
