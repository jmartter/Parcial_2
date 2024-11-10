package org.example.parcial_2.Webflux;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.parcial_2.Normal.ValorNormal;
import org.example.parcial_2.Normal.ValorNormalRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvService {

    private static final Logger logger = LoggerFactory.getLogger(CsvService.class);

    @Autowired
    private ValorNormalRepository valorNormalRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Flux<ValorNormal> loadCsvData() {
        return Flux.create(sink -> {
            try {
                // Truncate the table before loading new data
                valorNormalRepository.truncateTable();
                logger.info("Table truncated successfully");

                try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/valores_normales.csv"))) {
                    String[] line;
                    while ((line = reader.readNext()) != null) {
                        double valor = Double.parseDouble(line[0]);
                        ValorNormal valorNormal = new ValorNormal();
                        valorNormal.setValor(valor);
                        valorNormalRepository.save(valorNormal);
                        sink.next(valorNormal);
                        rabbitTemplate.convertAndSend("csvQueue", "Loaded ValorNormal: " + valorNormal);
                    }
                    sink.complete();
                }
            } catch (IOException | CsvValidationException | NumberFormatException e) {
                logger.error("Error processing CSV file", e);
                sink.error(e);
            }
        });
    }
}