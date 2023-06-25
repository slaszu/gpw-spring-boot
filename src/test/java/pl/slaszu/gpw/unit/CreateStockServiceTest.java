package pl.slaszu.gpw.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.slaszu.gpw.sharedkernel.domain.EventDispatcherInterface;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockCommand;
import pl.slaszu.gpw.stock.application.CreateStock.CreateStockService;
import pl.slaszu.gpw.stock.domain.model.Stock;
import pl.slaszu.gpw.stock.domain.repository.StockPriceRepositoryInterface;
import pl.slaszu.gpw.stock.domain.repository.StockRepositoryInterface;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class CreateStockServiceTest {

    private CreateStockService createStockService;

    private StockRepositoryInterface stockRepository;

    private Stock stock;

    @BeforeEach
    public void init() {
        this.stock = new Stock(UUID.randomUUID(), "pzu", "pzu s.a.");
        this.stockRepository = mock(StockRepositoryInterface.class);

        when(stockRepository.getByCode("kghm")).thenReturn(Optional.empty());
        when(stockRepository.getByCode("pzu")).thenReturn(Optional.of(stock));

        StockPriceRepositoryInterface stockPriceRepository = mock(StockPriceRepositoryInterface.class);
        EventDispatcherInterface eventDispatcher = mock(EventDispatcherInterface.class);

        this.createStockService = new CreateStockService(this.stockRepository, stockPriceRepository, eventDispatcher);
    }

    @Test
    public void testCreateStockNotExists() {
        CreateStockCommand command = new CreateStockCommand("kghm");
        this.createStockService.create(command);

        // save new stock, not prepared (insert)
        verify(this.stockRepository, never()).save(this.stock);
        verify(this.stockRepository).save(argThat(stock1 -> stock1.getCode().equals("kghm")));
        verify(this.stockRepository).save(argThat(stock1 -> stock1.getName() == null));
    }

    @Test
    public void testCreateStockAlreadyExists() {
        CreateStockCommand command = new CreateStockCommand("pzu");
        this.createStockService.create(command);

        // save prepared stock (update)
        verify(this.stockRepository).save(this.stock);
    }

}