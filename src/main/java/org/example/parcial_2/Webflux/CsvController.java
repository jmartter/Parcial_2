package org.example.parcial_2.Webflux;

import org.example.parcial_2.Normal.ValorNormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CsvController {

    @Autowired
    private CsvService csvService;

    @GetMapping("/load-csv")
    public Flux<ValorNormal> loadCsv(@RequestParam int batchSize) {
        return csvService.publishCsvData(batchSize);
    }

    @PostMapping("/pause-csv")
    public void pauseCsv() {
        csvService.pause();
    }

    @PostMapping("/resume-csv")
    public void resumeCsv() {
        csvService.resume();
    }
}
