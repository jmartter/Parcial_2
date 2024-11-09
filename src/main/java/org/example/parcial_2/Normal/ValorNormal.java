package org.example.parcial_2.Normal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "valor_normal")
public class ValorNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Double valor;

}