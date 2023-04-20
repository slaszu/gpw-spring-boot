package pl.slaszu.gpw.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pl.slaszu.gpw.stock.application.ListStocks.ListStocksService;
import pl.slaszu.gpw.stock.application.ListStocks.StockViewModel;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class StockRestControllerIntegrationIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ListStocksService service;

    @Test
    public void endpointStocksExists_returnEmptyArray() throws Exception {

        mvc.perform(get("/stocks")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().json("[]"));
    }

    @Test
    public void endpointStocksExists_returnGivenResults() throws Exception {

        List<StockViewModel> result = new ArrayList<>();
        result.add(new StockViewModel("uuid_123", "Stock_code_123"));

        when(service.getAllStocks()).thenReturn(result);

        mvc.perform(get("/stocks")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$.[0].id").value("uuid_123"))
            .andExpect(jsonPath("$.[0].code").value("Stock_code_123"));
    }
}
