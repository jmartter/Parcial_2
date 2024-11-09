package org.example.parcial_2.Webflux;

import org.example.parcial_2.Normal.ValorNormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CsvController {

    @Autowired
    private CsvService csvService;

    @GetMapping("/load-csv")
    public Flux<ValorNormal> loadCsv() {
        return csvService.loadCsvData();
    }
}