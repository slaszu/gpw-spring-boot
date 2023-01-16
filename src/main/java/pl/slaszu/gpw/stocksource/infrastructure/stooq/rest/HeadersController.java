package pl.slaszu.gpw.stocksource.infrastructure.stooq.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModel;
import pl.slaszu.gpw.stocksource.infrastructure.stooq.model.HeaderViewModelRepository;

import java.util.List;

@RestController
@RequestMapping("/sources/stooq/headers")
public class HeadersController {
    @Autowired
    private HeaderViewModelRepository headerViewModelRepository;


    @GetMapping("")
    public List<HeaderViewModel> headers() {
        return this.headerViewModelRepository.getAll();
    }

    @PutMapping("")
    public void create(@RequestBody HeaderViewModel header) {
        this.headerViewModelRepository.create(header);
    }

}
