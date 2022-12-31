package pl.slaszu.gpw.stock.domain.source;

public interface ProviderInterface {
    public Iterable<StockData> getAll();
}
