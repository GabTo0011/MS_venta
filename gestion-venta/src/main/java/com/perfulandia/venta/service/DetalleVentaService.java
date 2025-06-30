package com.perfulandia.venta.service;

import com.perfulandia.venta.dto.DetalleVentaDTO;
import com.perfulandia.venta.model.DetalleVenta;
import com.perfulandia.venta.model.Venta;
import com.perfulandia.venta.repository.DetalleVentaRepository;
import com.perfulandia.venta.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private VentaRepository ventaRepository;

    private DetalleVentaDTO toDTO(DetalleVenta entidad) {
        return new DetalleVentaDTO(
                entidad.getIdDetalle(),
                entidad.getVenta() != null ? entidad.getVenta().getIdVenta() : null,
                entidad.getIdProducto(),
                entidad.getCantidad(),
                entidad.getPrecioUnitario(),
                entidad.getSubtotal()
        );
    }

    private DetalleVenta toEntity(DetalleVentaDTO dto) {
        DetalleVenta entidad = new DetalleVenta();
        entidad.setIdDetalle(dto.getIdDetalle());
        entidad.setIdProducto(dto.getIdProducto());
        entidad.setCantidad(dto.getCantidad());
        entidad.setPrecioUnitario(dto.getPrecioUnitario());
        entidad.setSubtotal(dto.getSubtotal());

        if (dto.getIdVenta() != null) {
            Venta venta = ventaRepository.findById(dto.getIdVenta())
                    .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
            entidad.setVenta(venta);
        }

        return entidad;
    }

    public DetalleVentaDTO crear(DetalleVentaDTO dto) {
        DetalleVenta entidad = toEntity(dto);
        return toDTO(detalleVentaRepository.save(entidad));
    }

    public List<DetalleVentaDTO> listar() {
        return detalleVentaRepository.findAll()
                .stream()
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

        existente.setIdProducto(dto.getIdProducto());
        existente.setCantidad(dto.getCantidad());
        existente.setPrecioUnitario(dto.getPrecioUnitario());
        existente.setSubtotal(dto.getSubtotal());

        if (dto.getIdVenta() != null) {
            Venta venta = ventaRepository.findById(dto.getIdVenta())
                    .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
            existente.setVenta(venta);
        }

        return toDTO(detalleVentaRepository.save(existente));
    }

    public void eliminar(Integer id) {
        detalleVentaRepository.deleteById(id);
    }
}
