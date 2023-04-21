package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stock.domain.model.Stock;

@Service
@Slf4j
public class CreateStockFactory {

    public static Stock fillIfNeeded(Stock stock, String code, String name) {

        String codeStock = stock.getCode();
        String nameStock = stock.getName();

        // name
        if (nameStock == null || nameStock.equals("")) {
            if (name.equals("")) {
                name = null;
            }
            stock.setName(name);
        }

        // code
        if (codeStock == null || codeStock.equals("")) {
            if (code.equals("")) {
                code = null;
            }
            stock.setCode(code);
        }

        log.debug("stock to save: %s".formatted(stock));

        return stock;
    }

    public static Stock fillIfNeeded(Stock stock) {
        return fillIfNeeded(stock, "", "");
    }
}
