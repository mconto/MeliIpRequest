package com.localization.ip.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "distancias")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Distancias implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pais;
    private Double distancia;
    private Integer invocaciones = 0;

    @Version
    private Integer version;

    @PrePersist
    @PreUpdate
    public void incrementarInvocaciones() {
        if (invocaciones == null) {
            invocaciones = 0;
        }
        invocaciones++;
    }
}
