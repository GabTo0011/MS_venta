package com.perfulandia.venta.controller;

import com.perfulandia.venta.dto.VentaDTO;
import com.perfulandia.venta.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/ventas")
@Tag(name = "Ventas", description = "Gestión de ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    @Operation(summary = "Crear una venta")
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO dto) {
        return ResponseEntity.ok(ventaService.crear(dto));
    }

    @GetMapping
    @Operation(summary = "Listar todas las ventas")
    public ResponseEntity<List<VentaDTO>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una venta por ID")
    public ResponseEntity<VentaDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una venta por ID")
    public ResponseEntity<VentaDTO> actualizar(@PathVariable Integer id, @RequestBody VentaDTO dto) {
        return ResponseEntity.ok(ventaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una venta por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // HATEOAS
    @GetMapping("/hateoas/{id}")
    @Operation(summary = "Obtener una venta con HATEOAS por ID")
    public VentaDTO obtenerHATEOAS(@PathVariable Integer id) {
        VentaDTO dto = ventaService.obtenerPorId(id);

        dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(VentaController.class).listar()).withRel("todos"));
        dto.add(linkTo(methodOn(VentaController.class).eliminar(id)).withRel("eliminar"));

        dto.add(Link.of("http://localhost:8081/ventas/" + dto.getIdVenta()).withSelfRel());
        dto.add(Link.of("http://localhost:8081/ventas/" + dto.getIdVenta()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8081/ventas/" + dto.getIdVenta()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return dto;
    }

    @GetMapping("/hateoas")
    @Operation(summary = "Listar todas las ventas con HATEOAS")
    public List<VentaDTO> obtenerTodasHATEOAS() {
        List<VentaDTO> lista = ventaService.listar();

        for (VentaDTO dto : lista) {
            dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(dto.getIdVenta())).withSelfRel());
            dto.add(Link.of("http://localhost:8081/ventas").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8081/ventas/" + dto.getIdVenta()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
}
