package com.perfulandia.venta.service;

import com.perfulandia.venta.dto.DetalleVentaDTO;
import com.perfulandia.venta.model.DetalleVenta;
import com.perfulandia.venta.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    private DetalleVentaDTO toDTO(DetalleVenta entidad) {
        return new DetalleVentaDTO(
                entidad.getIdDetalle(),
                entidad.getIdVenta(),
                entidad.getIdProducto(),
                entidad.getCantidad(),
                entidad.getPrecioUnitario(),
                entidad.getSubtotal()
        );
    }

    private DetalleVenta toEntity(DetalleVentaDTO dto) {
        DetalleVenta entidad = new DetalleVenta();
        entidad.setIdDetalle(dto.getIdDetalle());
        entidad.setIdVenta(dto.getIdVenta());
        entidad.setIdProducto(dto.getIdProducto());
        entidad.setCantidad(dto.getCantidad());
        entidad.setPrecioUnitario(dto.getPrecioUnitario());
        entidad.setSubtotal(dto.getSubtotal());
        return entidad;
    }

    public DetalleVentaDTO crear(DetalleVentaDTO dto) {
        DetalleVenta entidad = toEntity(dto);
        return toDTO(detalleVentaRepository.save(entidad));
    }

    public List<DetalleVentaDTO> listar() {
        return detalleVentaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DetalleVentaDTO obtenerPorId(Integer id) {
        DetalleVenta entidad = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleVenta no encontrado"));
        return toDTO(entidad);
    }

    public DetalleVentaDTO actualizar(Integer id, DetalleVentaDTO dto) {
        DetalleVenta existente = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleVenta no encontrado"));

        existente.setIdVenta(dto.getIdVenta());
        existente.setIdProducto(dto.getIdProducto());
        existente.setCantidad(dto.getCantidad());
        existente.setPrecioUnitario(dto.getPrecioUnitario());
        existente.setSubtotal(dto.getSubtotal());

        return toDTO(detalleVentaRepository.save(existente));
    }

    public void eliminar(Integer id) {
        detalleVentaRepository.deleteById(id);
    }
}
