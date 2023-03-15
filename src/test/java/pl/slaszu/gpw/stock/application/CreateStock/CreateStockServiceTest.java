//package pl.slaszu.gpw.stock.application.CreateStock;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import pl.slaszu.gpw.stock.domain.model.Stock;
//import pl.slaszu.gpw.stock.domain.repository.StockPriceRepositoryInterface;
//import pl.slaszu.gpw.stock.domain.repository.StockRepositoryInterface;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class CreateStockServiceTest {
//
//    private CreateStockService createStockService;
//
//    @BeforeEach
//    public void init() {
//        Stock stock = mock(Stock.class);
//
//        StockRepositoryInterface stockRepository = mock(StockRepositoryInterface.class);
//        when(stockRepository.getByCode("kghm")).thenReturn(Optional.empty());
//        when(stockRepository.getByCode("pzu")).thenReturn(Optional.of(stock));
//
//        StockPriceRepositoryInterface stockPriceRepository = mock(StockPriceRepositoryInterface.class);
//
//        // TODO: 18.01.2023 dodoac jakies testy zwiazane ze sprawdzaniem wywolywanych metod w mockach
//        this.createStockService = new CreateStockService(stockRepository, stockPriceRepository);
//    }
//
//    @Test
//    public void testCreateStockNotExists() {
//        CreateStockCommand command = new CreateStockCommand("kghm");
//        assertDoesNotThrow(() -> this.createStockService.create(command));
//    }
//
//    @Test
//    public void testCreateStockAlreadyExists() {
//        CreateStockCommand command = new CreateStockCommand("pzu");
//        this.createStockService.create(command);
//    }
//
//}