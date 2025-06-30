package com.perfulandia.venta.controller;

import com.perfulandia.venta.dto.DetalleVentaDTO;
import com.perfulandia.venta.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping
    public DetalleVentaDTO crear(@RequestBody DetalleVentaDTO dto) {
        return detalleVentaService.crear(dto);
    }

    @GetMapping
    public List<DetalleVentaDTO> listar() {
        return detalleVentaService.listar();
    }

    @GetMapping("/{id}")
    public DetalleVentaDTO obtener(@PathVariable Integer id) {
        return detalleVentaService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public DetalleVentaDTO actualizar(@PathVariable Integer id, @RequestBody DetalleVentaDTO dto) {
        return detalleVentaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        detalleVentaService.eliminar(id);
    }
}
