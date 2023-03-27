package pl.slaszu.gpw.stock.application.CreateStock;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateStockCommand {

    private String code;

    private String name;

    private CreateStockPriceCommand createStockPriceCommand;

    public CreateStockCommand(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public CreateStockCommand(String code) {
        this.code = code;
        this.name = "";
    }
}
