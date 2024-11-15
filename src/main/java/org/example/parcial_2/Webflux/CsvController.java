package org.example.parcial_2.Webflux;

import org.example.parcial_2.Normal.ValorNormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CsvController {

    @Autowired
    private CsvService csvService;

    // Método para cargar datos del CSV
    @GetMapping("/load-csv")
    public Flux<ValorNormal> loadCsv() {
        return csvService.publishCsvData();
    }

    // Método para pausar la carga de datos del CSV
    @PostMapping("/pause-csv")
    public void pauseCsv() {
        csvService.pause();
    }

    // Método para reanudar la carga de datos del CSV
    @PostMapping("/resume-csv")
    public void resumeCsv() {
        csvService.resume();
    }
}