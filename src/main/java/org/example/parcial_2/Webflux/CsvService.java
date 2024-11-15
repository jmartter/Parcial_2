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
import java.time.Duration;

@Service
public class CsvService {
    private static final Logger logger = LoggerFactory.getLogger(CsvService.class);

    @Autowired
    private ValorNormalRepository valorNormalRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private volatile boolean isPaused = false;

    // Método para publicar datos del CSV como un flujo reactivo
    public Flux<ValorNormal> publishCsvData() {
        return Flux.<ValorNormal>create(sink -> {
            try {
                // Truncar la tabla antes de cargar nuevos datos
                valorNormalRepository.truncateTable();
                logger.info("Table truncated successfully");

                // Leer el archivo CSV
                try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/valores_normales.csv"))) {
                    String[] line;
                    while ((line = reader.readNext()) != null) {
                        // Pausar si es necesario
                        while (isPaused) {
                            Thread.sleep(100); // Esperar hasta que se reanude
                        }
                        // Procesar cada línea del CSV
                        double valor = Double.parseDouble(line[0]);
                        ValorNormal valorNormal = new ValorNormal();
                        valorNormal.setValor(valor);
                        valorNormalRepository.save(valorNormal);
                        rabbitTemplate.convertAndSend("csvQueue", "Loaded ValorNormal: " + valorNormal);
                        sink.next(valorNormal);
                        Thread.sleep(30);
                    }
                    // Enviar mensaje de finalización
                    rabbitTemplate.convertAndSend("csvQueue", "CSV loading completed");
                    sink.complete();
                }
            } catch (IOException | CsvValidationException | InterruptedException e) {
                logger.error("Error processing CSV file", e);
                sink.error(e);
            }
        }).delayElements(Duration.ofMillis(30));
    }

    // Método para pausar la carga de datos
    public void pause() {
        isPaused = true;
    }

    // Método para reanudar la carga de datos
    public void resume() {
        isPaused = false;
    }
}