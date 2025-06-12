package com.perfulandia.venta.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Integer id;
    private LocalDateTime fechaVenta;
    private String nombreCliente;
    private Double total;
    private List<DetalleVentaDTO> detalles;
}
