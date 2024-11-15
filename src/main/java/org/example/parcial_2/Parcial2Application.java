package org.example.parcial_2;

import org.example.parcial_2.Webflux.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Parcial2Application {

    @Autowired
    private CsvService csvService;

    // Método principal que inicia la aplicación Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(Parcial2Application.class, args);
    }

    // Bean que se ejecuta al iniciar la aplicación y publica los datos del CSV
    @Bean
    public CommandLineRunner run() {
        return args -> {
            csvService.publishCsvData().subscribe();
        };
    }
}