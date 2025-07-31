package com.rumbo.rumbo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalle_factura")
@Data
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private double precio;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;
}


