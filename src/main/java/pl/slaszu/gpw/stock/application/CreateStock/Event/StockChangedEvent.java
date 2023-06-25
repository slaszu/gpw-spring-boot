package pl.slaszu.gpw.stock.application.CreateStock.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.slaszu.gpw.stock.domain.model.Stock;

@AllArgsConstructor
@Getter
public class StockChangedEvent {
    private Stock stock;
}
