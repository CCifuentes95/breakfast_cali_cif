package co.edu.usbcali.breakfast.presentation.backingBeans;

import co.edu.usbcali.breakfast.exceptions.*;
import co.edu.usbcali.breakfast.modelo.*;
import co.edu.usbcali.breakfast.modelo.dto.UsuarioDTO;
import co.edu.usbcali.breakfast.presentation.businessDelegate.*;
import co.edu.usbcali.breakfast.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class CambiarContrasenaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CambiarContrasenaView.class);
    
    private Password txtClave;
    private Password txtNuevo;
    private Password txtConfirmarNuevo;
    
    private Usuario usuario;
    
    private CommandButton btnSave;
    private CommandButton btnRegresar;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public void modificarAction() throws Exception {
		usuario = businessDelegatorView.getUsuario(1L);
		try {
			if (txtClave.getValue().toString().trim().equals(usuario.getClave())) {
				if (txtNuevo.getValue().toString().trim().equals(txtConfirmarNuevo.getValue().toString().trim())) {
					usuario.setClave(txtClave.getValue().toString().trim());
					
					businessDelegatorView.updateUsuario(usuario);
				}else {
					throw new Exception("La contraseña no concuerda");
				}
			}else {
				throw new Exception("La clave no concuerda con la del usuario");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
    
    public String regresarAction() {
		return "/login.xhtml";
	}

	public Password getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(Password txtClave) {
		this.txtClave = txtClave;
	}

	public Password getTxtNuevo() {
		return txtNuevo;
	}

	public void setTxtNuevo(Password txtNuevo) {
		this.txtNuevo = txtNuevo;
	}

	public Password getTxtConfirmarNuevo() {
		return txtConfirmarNuevo;
	}

	public void setTxtConfirmarNuevo(Password txtConfirmarNuevo) {
		this.txtConfirmarNuevo = txtConfirmarNuevo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(CommandButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

    
}
