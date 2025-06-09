package com.perfulandia.venta.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;
    private Date fechaVenta;
}
