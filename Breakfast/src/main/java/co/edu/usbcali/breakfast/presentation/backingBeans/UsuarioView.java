package co.edu.usbcali.breakfast.presentation.backingBeans;

import co.edu.usbcali.breakfast.exceptions.*;
import co.edu.usbcali.breakfast.modelo.*;
import co.edu.usbcali.breakfast.modelo.dto.UsuarioDTO;
import co.edu.usbcali.breakfast.presentation.businessDelegate.*;
import co.edu.usbcali.breakfast.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

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
public class UsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioView.class);
    private InputText txtClave;
    private InputText txtDireccion;
    private InputText txtEmail;
    private InputText txtNombre;
    private InputText txtUsuario;
    private InputText txtCodigoUsuario;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuarioDTO> data;
    private UsuarioDTO selectedUsuario;
    private Usuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuarioDTO usuarioDTO = (UsuarioDTO) e.getObject();

            if (txtClave == null) {
                txtClave = new InputText();
            }

            txtClave.setValue(usuarioDTO.getClave());

            if (txtDireccion == null) {
                txtDireccion = new InputText();
            }

            txtDireccion.setValue(usuarioDTO.getDireccion());

            if (txtEmail == null) {
                txtEmail = new InputText();
            }

            txtEmail.setValue(usuarioDTO.getEmail());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(usuarioDTO.getNombre());

            if (txtUsuario == null) {
                txtUsuario = new InputText();
            }

            txtUsuario.setValue(usuarioDTO.getUsuario());

            if (txtCodigoUsuario == null) {
                txtCodigoUsuario = new InputText();
            }

            txtCodigoUsuario.setValue(usuarioDTO.getCodigoUsuario());

            Long codigoUsuario = FacesUtils.checkLong(txtCodigoUsuario);
            entity = businessDelegatorView.getUsuario(codigoUsuario);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuario = null;

        if (txtClave != null) {
            txtClave.setValue(null);
            txtClave.setDisabled(true);
        }

        if (txtDireccion != null) {
            txtDireccion.setValue(null);
            txtDireccion.setDisabled(true);
        }

        if (txtEmail != null) {
            txtEmail.setValue(null);
            txtEmail.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtUsuario != null) {
            txtUsuario.setValue(null);
            txtUsuario.setDisabled(true);
        }

        if (txtCodigoUsuario != null) {
            txtCodigoUsuario.setValue(null);
            txtCodigoUsuario.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long codigoUsuario = FacesUtils.checkLong(txtCodigoUsuario);
            entity = (codigoUsuario != null)
                ? businessDelegatorView.getUsuario(codigoUsuario) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtClave.setDisabled(false);
            txtDireccion.setDisabled(false);
            txtEmail.setDisabled(false);
            txtNombre.setDisabled(false);
            txtUsuario.setDisabled(false);
            txtCodigoUsuario.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtClave.setValue(entity.getClave());
            txtClave.setDisabled(false);
            txtDireccion.setValue(entity.getDireccion());
            txtDireccion.setDisabled(false);
            txtEmail.setValue(entity.getEmail());
            txtEmail.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtUsuario.setValue(entity.getUsuario());
            txtUsuario.setDisabled(false);
            txtCodigoUsuario.setValue(entity.getCodigoUsuario());
            txtCodigoUsuario.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsuario"));
        txtClave.setValue(selectedUsuario.getClave());
        txtClave.setDisabled(false);
        txtDireccion.setValue(selectedUsuario.getDireccion());
        txtDireccion.setDisabled(false);
        txtEmail.setValue(selectedUsuario.getEmail());
        txtEmail.setDisabled(false);
        txtNombre.setValue(selectedUsuario.getNombre());
        txtNombre.setDisabled(false);
        txtUsuario.setValue(selectedUsuario.getUsuario());
        txtUsuario.setDisabled(false);
        txtCodigoUsuario.setValue(selectedUsuario.getCodigoUsuario());
        txtCodigoUsuario.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuario == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Usuario();

            Long codigoUsuario = FacesUtils.checkLong(txtCodigoUsuario);

            entity.setClave(FacesUtils.checkString(txtClave));
            entity.setCodigoUsuario(codigoUsuario);
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setUsuario(FacesUtils.checkString(txtUsuario));
            businessDelegatorView.saveUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long codigoUsuario = new Long(selectedUsuario.getCodigoUsuario());
                entity = businessDelegatorView.getUsuario(codigoUsuario);
            }

            entity.setClave(FacesUtils.checkString(txtClave));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setUsuario(FacesUtils.checkString(txtUsuario));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Long codigoUsuario = new Long(selectedUsuario.getCodigoUsuario());
            entity = businessDelegatorView.getUsuario(codigoUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoUsuario = FacesUtils.checkLong(txtCodigoUsuario);
            entity = businessDelegatorView.getUsuario(codigoUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Long codigoUsuario = new Long(selectedUsuario.getCodigoUsuario());
            entity = businessDelegatorView.getUsuario(codigoUsuario);
            businessDelegatorView.deleteUsuario(entity);
            data.remove(selectedUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String clave, Long codigoUsuario,
        String direccion, String email, String nombre, String usuario)
        throws Exception {
        try {
            entity.setClave(FacesUtils.checkString(clave));
            entity.setDireccion(FacesUtils.checkString(direccion));
            entity.setEmail(FacesUtils.checkString(email));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setUsuario(FacesUtils.checkString(usuario));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(InputText txtClave) {
        this.txtClave = txtClave;
    }

    public InputText getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(InputText txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public InputText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(InputText txtEmail) {
        this.txtEmail = txtEmail;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(InputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public InputText getTxtCodigoUsuario() {
        return txtCodigoUsuario;
    }

    public void setTxtCodigoUsuario(InputText txtCodigoUsuario) {
        this.txtCodigoUsuario = txtCodigoUsuario;
    }

    public List<UsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuarioDTO> usuarioDTO) {
        this.data = usuarioDTO;
    }

    public UsuarioDTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioDTO usuario) {
        this.selectedUsuario = usuario;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
