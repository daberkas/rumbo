package com.rumbo.rumbo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "facturas")
@Data
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String concepto;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalles;

    public double getTotalSinIva() {
        return detalles != null
            ? detalles.stream().mapToDouble(DetalleFactura::getPrecio).sum()
            : 0;
    }

    public double getIva() {
        return getTotalSinIva() * 0.21;
    }

    public double getTotalConIva() {
        return getTotalSinIva() + getIva();
    }
}


