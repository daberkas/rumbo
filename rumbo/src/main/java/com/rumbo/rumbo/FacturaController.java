package com.rumbo.rumbo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaRepository facturaRepo;
    private final ClienteRepository clienteRepo;
    private final DetalleFacturaRepository detalleRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("facturas", facturaRepo.findAll());
        return "facturas/listar";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("factura", new Factura());
        model.addAttribute("clientes", clienteRepo.findAll());
        return "facturas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Factura factura) {
        facturaRepo.save(factura);
        return "redirect:/facturas";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {
        Factura factura = facturaRepo.findById(id).orElseThrow();
        model.addAttribute("factura", factura);
        model.addAttribute("detalle", new DetalleFactura());
        return "facturas/ver";
    }

        @PostMapping("/agregar-detalle/{id}")
    public String agregarDetalle(@PathVariable Long id, @ModelAttribute DetalleFactura detalle) {
        System.out.println("Descripción: " + detalle.getDescripcion());
        System.out.println("Precio: " + detalle.getPrecio());
        System.out.println("Factura.id en objeto: " + (detalle.getFactura() != null ? detalle.getFactura().getId() : "null"));

        Factura factura = facturaRepo.findById(id).orElseThrow();
        detalle.setId(null);
        detalle.setFactura(factura);
        detalleRepo.save(detalle);
        return "redirect:/facturas/ver/" + id;
    }


/*     @PostMapping("/agregar-detalle/{id}")
    public String agregarDetalle(@PathVariable Long id, @ModelAttribute DetalleFactura detalle) {
        Factura factura = facturaRepo.findById(id).orElseThrow();
        detalle.setFactura(factura);
        detalleRepo.save(detalle);
        return "redirect:/facturas/ver/" + id;
    } */
}


