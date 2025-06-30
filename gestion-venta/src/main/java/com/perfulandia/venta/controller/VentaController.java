package com.perfulandia.venta.controller;

import com.perfulandia.venta.dto.VentaDTO;
import com.perfulandia.venta.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public VentaDTO crear(@RequestBody VentaDTO dto) {
        return ventaService.crear(dto);
    }

    @GetMapping
    public List<VentaDTO> listar() {
        return ventaService.listar();
    }

    @GetMapping("/{id}")
    public VentaDTO obtener(@PathVariable Integer id) {
        return ventaService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public VentaDTO actualizar(@PathVariable Integer id, @RequestBody VentaDTO dto) {
        return ventaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
    }
}
