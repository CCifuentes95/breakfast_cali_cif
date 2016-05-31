package co.edu.usbcali.breakfast.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class PedidoproductoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PedidoproductoDTO.class);
    private Long codigoPedidoProducto;
    private Long codigoTipoproducto;
    private Long codigoUsuario;
    private Long codigoPedido_Pedido;
    private Long codigoProducto_Producto;

    public Long getCodigoPedidoProducto() {
        return codigoPedidoProducto;
    }

    public void setCodigoPedidoProducto(Long codigoPedidoProducto) {
        this.codigoPedidoProducto = codigoPedidoProducto;
    }

    public Long getCodigoTipoproducto() {
        return codigoTipoproducto;
    }

    public void setCodigoTipoproducto(Long codigoTipoproducto) {
        this.codigoTipoproducto = codigoTipoproducto;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Long getCodigoPedido_Pedido() {
        return codigoPedido_Pedido;
    }

    public void setCodigoPedido_Pedido(Long codigoPedido_Pedido) {
        this.codigoPedido_Pedido = codigoPedido_Pedido;
    }

    public Long getCodigoProducto_Producto() {
        return codigoProducto_Producto;
    }

    public void setCodigoProducto_Producto(Long codigoProducto_Producto) {
        this.codigoProducto_Producto = codigoProducto_Producto;
    }
}
