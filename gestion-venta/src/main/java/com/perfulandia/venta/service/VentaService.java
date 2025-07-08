package com.perfulandia.venta.service;

import com.perfulandia.venta.dto.DetalleVentaDTO;
import com.perfulandia.venta.dto.VentaDTO;
import com.perfulandia.venta.model.Venta;
// import com.perfulandia.venta.repository.DetalleVentaRepository;
import com.perfulandia.venta.repository.VentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    // @Autowired
    // private DetalleVentaRepository detalleVentaRepository;

    private VentaDTO toDTO(Venta venta) {
        List<DetalleVentaDTO> detalleDTOs = venta.getDetalles() != null
                ? venta.getDetalles().stream().map(det -> new DetalleVentaDTO(
                        det.getIdDetalle(),
                        venta.getIdVenta(),
                        det.getIdProducto(),
                        det.getCantidad(),
                        det.getPrecioUnitario(),
                        det.getSubtotal()
                )).collect(Collectors.toList())
                : List.of();

        double total = detalleDTOs.stream().mapToDouble(DetalleVentaDTO::getSubtotal).sum();

        return new VentaDTO(
                venta.getIdVenta(),
                venta.getIdCliente(),
                venta.getIdVendedor(),
                venta.getFechaVenta(),
                "Nombre Cliente Simulado",
                "Nombre Vendedor Simulado",
                total,
                detalleDTOs
        );
    }

    private Venta toEntity(VentaDTO dto) {
        Venta venta = new Venta();
        venta.setIdVenta(dto.getIdVenta());
        venta.setIdCliente(dto.getIdCliente());
        venta.setIdVendedor(dto.getIdVendedor());
        venta.setFechaVenta(dto.getFechaVenta());
        return venta;
    }

    public VentaDTO crear(VentaDTO dto) {
        Venta venta = toEntity(dto);
        return toDTO(ventaRepository.save(venta));
    }

    public List<VentaDTO> listar() {
        return ventaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public VentaDTO obtenerPorId(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id " + id));
        return toDTO(venta);
    }

    public VentaDTO actualizar(Integer id, VentaDTO dto) {
        Venta existente = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id " + id));

        existente.setIdCliente(dto.getIdCliente());
        existente.setIdVendedor(dto.getIdVendedor());
        existente.setFechaVenta(dto.getFechaVenta());

        return toDTO(ventaRepository.save(existente));
    }

    public void eliminar(Integer id) {
        if (!ventaRepository.existsById(id)) {
            throw new EntityNotFoundException("Venta no encontrada con id " + id);
        }
        ventaRepository.deleteById(id);
    }
}
