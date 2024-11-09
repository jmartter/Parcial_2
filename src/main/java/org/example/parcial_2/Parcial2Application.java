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

    public static void main(String[] args) {
        SpringApplication.run(Parcial2Application.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            csvService.loadCsvData().subscribe();
        };
    }
}