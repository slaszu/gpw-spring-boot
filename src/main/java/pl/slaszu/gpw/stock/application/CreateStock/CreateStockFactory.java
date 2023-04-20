package pl.slaszu.gpw.stock.application.CreateStock;

import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stock.domain.model.Stock;

@Service
public class CreateStockFactory {

    public static Stock fillIfNeeded(Stock stock, String code, String name) {
        if (stock.getName() == null && !name.equals("")) {
            stock.setName(name);
        }

        if (stock.getCode() == null && !code.equals("")) {
            stock.setCode(code);
        }

        return stock;
    }
}
