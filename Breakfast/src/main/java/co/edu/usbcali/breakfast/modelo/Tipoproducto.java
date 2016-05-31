package co.edu.usbcali.breakfast.modelo;
// Generated 31/05/2016 10:38:31 AM by Hibernate Tools 4.3.1.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Tipoproducto generated by hbm2java
 */
public class Tipoproducto  implements java.io.Serializable {


     private Long codigoTipoproducto;
     private String descripcion;
     private Set<Producto> productos = new HashSet<Producto>(0);

    public Tipoproducto() {
    }

	
    public Tipoproducto(Long codigoTipoproducto, String descripcion) {
        this.codigoTipoproducto = codigoTipoproducto;
        this.descripcion = descripcion;
    }
    public Tipoproducto(Long codigoTipoproducto, String descripcion, Set<Producto> productos) {
       this.codigoTipoproducto = codigoTipoproducto;
       this.descripcion = descripcion;
       this.productos = productos;
    }
   
    public Long getCodigoTipoproducto() {
        return this.codigoTipoproducto;
    }
    
    public void setCodigoTipoproducto(Long codigoTipoproducto) {
        this.codigoTipoproducto = codigoTipoproducto;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Producto> getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }




}

