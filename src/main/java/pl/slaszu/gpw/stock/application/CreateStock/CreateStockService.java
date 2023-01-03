package pl.slaszu.gpw.stock.application.CreateStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.repository.StockRepositoryInterface;

import java.util.UUID;

@Service
public class CreateStockService {
    @Autowired
    private StockRepositoryInterface stockRepositoryInterface;

    public void create(CreateStockCommand command) throws CreateStockException {

        if (this.stockRepositoryInterface.getByCode(command.getCode()).isPresent()) {
            throw new CreateStockException("Stock with code %s already exists".formatted(command.getCode()));
        }

        UUID uuid = UUID.randomUUID();
        Stock stock = new Stock(uuid, command.getCode());

        this.stockRepositoryInterface.save(stock);
    }
}
