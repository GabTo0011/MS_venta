package com.perfulandia.venta.dto;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;
    private Date fechaVenta;

    private String nombreCliente;
    private String nombreVendedor;
    private Double total;
    private List<DetalleVentaDTO> detalles;
}
