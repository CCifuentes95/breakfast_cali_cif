package co.edu.usbcali.breakfast.modelo;
// Generated 31/05/2016 10:38:31 AM by Hibernate Tools 4.3.1.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Long codigoUsuario;
     private String nombre;
     private String usuario;
     private String clave;
     private String email;
     private String direccion;
     private Set<Pedido> pedidos = new HashSet<Pedido>(0);

    public Usuario() {
    }

	
    public Usuario(Long codigoUsuario, String nombre, String usuario, String clave, String email, String direccion) {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.email = email;
        this.direccion = direccion;
    }
    public Usuario(Long codigoUsuario, String nombre, String usuario, String clave, String email, String direccion, Set<Pedido> pedidos) {
       this.codigoUsuario = codigoUsuario;
       this.nombre = nombre;
       this.usuario = usuario;
       this.clave = clave;
       this.email = email;
       this.direccion = direccion;
       this.pedidos = pedidos;
    }
   
    public Long getCodigoUsuario() {
        return this.codigoUsuario;
    }
    
    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Set<Pedido> getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }




}


