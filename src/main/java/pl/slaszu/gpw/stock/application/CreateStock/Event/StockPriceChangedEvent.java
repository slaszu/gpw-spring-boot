package pl.slaszu.gpw.stock.application.CreateStock.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.slaszu.gpw.stock.domain.model.StockPrice;

@AllArgsConstructor
@Getter
public class StockPriceChangedEvent {
    private StockPrice stockPrice;
}
