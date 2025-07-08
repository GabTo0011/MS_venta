package com.perfulandia.venta.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO extends RepresentationModel<VentaDTO> {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;
    private Date fechaVenta;

    private String nombreCliente;
    private String nombreVendedor;
    private Double total;
    private List<DetalleVentaDTO> detalles;
}
