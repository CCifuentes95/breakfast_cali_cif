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
public class TipoproductoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoproductoDTO.class);
    private Long codigoTipoproducto;
    private String descripcion;

    public Long getCodigoTipoproducto() {
        return codigoTipoproducto;
    }

    public void setCodigoTipoproducto(Long codigoTipoproducto) {
        this.codigoTipoproducto = codigoTipoproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
