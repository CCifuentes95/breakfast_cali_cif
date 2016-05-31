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
public class ProductoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProductoDTO.class);
    private Long codigoProducto;
    private Long descripcion;
    private String imagen;
    private String nombre;
    private Long precio;
    private Long codigoTipoproducto_Tipoproducto;

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Long getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Long descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getCodigoTipoproducto_Tipoproducto() {
        return codigoTipoproducto_Tipoproducto;
    }

    public void setCodigoTipoproducto_Tipoproducto(
        Long codigoTipoproducto_Tipoproducto) {
        this.codigoTipoproducto_Tipoproducto = codigoTipoproducto_Tipoproducto;
    }
}
