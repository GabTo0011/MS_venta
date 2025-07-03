package com.perfulandia.venta.controller;

import com.perfulandia.venta.dto.DetalleVentaDTO;
import com.perfulandia.venta.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/detalles")
@Tag(name = "Detalles de Venta", description = "Gestión de detalles de ventas")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping
    @Operation(summary = "Crear un detalle de venta")
    public ResponseEntity<DetalleVentaDTO> crear(@RequestBody DetalleVentaDTO dto) {
        return ResponseEntity.ok(detalleVentaService.crear(dto));
    }

    @GetMapping
    @Operation(summary = "Listar todos los detalles de venta")
    public ResponseEntity<List<DetalleVentaDTO>> listar() {
        return ResponseEntity.ok(detalleVentaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un detalle de venta por ID")
    public ResponseEntity<DetalleVentaDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(detalleVentaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un detalle de venta por ID")
    public ResponseEntity<DetalleVentaDTO> actualizar(@PathVariable Integer id, @RequestBody DetalleVentaDTO dto) {
        return ResponseEntity.ok(detalleVentaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un detalle de venta por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        detalleVentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // HATEOAS
    @GetMapping("/hateoas/{id}")
    @Operation(summary = "Obtener un detalle de venta con HATEOAS por ID")
    public DetalleVentaDTO obtenerHATEOAS(@PathVariable Integer id) {
        DetalleVentaDTO dto = detalleVentaService.obtenerPorId(id);

        dto.add(linkTo(methodOn(DetalleVentaController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(DetalleVentaController.class).listar()).withRel("todos"));
        dto.add(linkTo(methodOn(DetalleVentaController.class).eliminar(id)).withRel("eliminar"));

        dto.add(Link.of("http://localhost:8081/detalles/" + dto.getIdDetalle()).withSelfRel());
        dto.add(Link.of("http://localhost:8081/detalles/" + dto.getIdDetalle()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8081/detalles/" + dto.getIdDetalle()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return dto;
    }

    @GetMapping("/hateoas")
    @Operation(summary = "Listar todos los detalles de venta con HATEOAS")
    public List<DetalleVentaDTO> obtenerTodosHATEOAS() {
        List<DetalleVentaDTO> lista = detalleVentaService.listar();

        for (DetalleVentaDTO dto : lista) {
            dto.add(linkTo(methodOn(DetalleVentaController.class).obtenerHATEOAS(dto.getIdDetalle())).withSelfRel());
            dto.add(Link.of("http://localhost:8081/detalles").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8081/detalles/" + dto.getIdDetalle()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
}
