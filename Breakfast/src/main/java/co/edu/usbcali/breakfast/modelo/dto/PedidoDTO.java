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
public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PedidoDTO.class);
    private Long codigoPedido;
    private Date fecha;
    private Long codigoUsuario_Usuario;

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getCodigoUsuario_Usuario() {
        return codigoUsuario_Usuario;
    }

    public void setCodigoUsuario_Usuario(Long codigoUsuario_Usuario) {
        this.codigoUsuario_Usuario = codigoUsuario_Usuario;
    }
}
