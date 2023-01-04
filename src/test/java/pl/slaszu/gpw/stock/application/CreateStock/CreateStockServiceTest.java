package pl.slaszu.gpw.stock.application.CreateStock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.repository.StockRepositoryInterface;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateStockServiceTest {

    private CreateStockService createStockService;

    @BeforeEach
    public void init() {
        Stock stock = mock(Stock.class);

        StockRepositoryInterface stockRepository = mock(StockRepositoryInterface.class);
        when(stockRepository.getByCode("kghm")).thenReturn(Optional.empty());
        when(stockRepository.getByCode("pzu")).thenReturn(Optional.of(stock));

        this.createStockService = new CreateStockService(stockRepository);
    }

    @Test
    public void testCreateStockNotExists() {
        CreateStockCommand command = new CreateStockCommand("kghm");
        assertDoesNotThrow(() -> this.createStockService.create(command));
    }

    @Test
    public void testCreateStockAlreadyExists() {
        CreateStockCommand command = new CreateStockCommand("pzu");
        assertThrows(CreateStockException.class, () -> this.createStockService.create(command));
    }

}