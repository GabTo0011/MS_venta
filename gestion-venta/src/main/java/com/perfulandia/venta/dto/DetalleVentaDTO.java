package com.perfulandia.venta.dto;

<<<<<<< HEAD
public class DetalleVentaDTO {

=======
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private Integer idDetalle;
    private Integer idVenta;
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
>>>>>>> 5052071a85022015b14d84c5fdee83de55dd2f1d
}
