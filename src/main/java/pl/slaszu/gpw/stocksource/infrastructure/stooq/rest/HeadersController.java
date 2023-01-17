package pl.slaszu.gpw.stocksource.infrastructure.stooq.rest;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModel;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModelRepository;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.service.HeaderManager;

import java.util.List;

@RestController
@RequestMapping("/sources/stooq/headers")
public class HeadersController {
    @Autowired
    private HeaderViewModelRepository headerViewModelRepository;

    @Autowired
    private HeaderManager headerManager;


    @GetMapping("")
    public List<HeaderViewModel> headers() {
        return this.headerViewModelRepository.getAll();
    }

    @PostMapping("")
    @Operation(description = "Add new or update exists header be name")
    public void create(@RequestBody HeaderViewModel header) {
        this.headerManager.createOrUpdate(header);
    }

    @DeleteMapping("/{headerName}")
    public void delete(@PathVariable String headerName) {
        this.headerManager.delete(headerName);
    }

}
