package co.edu.usbcali.breakfast.presentation.backingBeans;

import co.edu.usbcali.breakfast.exceptions.*;
import co.edu.usbcali.breakfast.modelo.*;
import co.edu.usbcali.breakfast.modelo.dto.PedidoDTO;
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
public class PedidoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PedidoView.class);
    private InputText txtCodigoUsuario_Usuario;
    private InputText txtCodigoPedido;
    private Calendar txtFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PedidoDTO> data;
    private PedidoDTO selectedPedido;
    private Pedido entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PedidoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PedidoDTO pedidoDTO = (PedidoDTO) e.getObject();

            if (txtCodigoUsuario_Usuario == null) {
                txtCodigoUsuario_Usuario = new InputText();
            }

            txtCodigoUsuario_Usuario.setValue(pedidoDTO.getCodigoUsuario_Usuario());

            if (txtCodigoPedido == null) {
                txtCodigoPedido = new InputText();
            }

            txtCodigoPedido.setValue(pedidoDTO.getCodigoPedido());

            if (txtFecha == null) {
                txtFecha = new Calendar();
            }

            txtFecha.setValue(pedidoDTO.getFecha());

            Long codigoPedido = FacesUtils.checkLong(txtCodigoPedido);
            entity = businessDelegatorView.getPedido(codigoPedido);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPedido = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPedido = null;

        if (txtCodigoUsuario_Usuario != null) {
            txtCodigoUsuario_Usuario.setValue(null);
            txtCodigoUsuario_Usuario.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(true);
        }

        if (txtCodigoPedido != null) {
            txtCodigoPedido.setValue(null);
            txtCodigoPedido.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long codigoPedido = FacesUtils.checkLong(txtCodigoPedido);
            entity = (codigoPedido != null)
                ? businessDelegatorView.getPedido(codigoPedido) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigoUsuario_Usuario.setDisabled(false);
            txtFecha.setDisabled(false);
            txtCodigoPedido.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtCodigoUsuario_Usuario.setValue(entity.getUsuario()
                                                    .getCodigoUsuario());
            txtCodigoUsuario_Usuario.setDisabled(false);
            txtCodigoPedido.setValue(entity.getCodigoPedido());
            txtCodigoPedido.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPedido = (PedidoDTO) (evt.getComponent().getAttributes()
                                         .get("selectedPedido"));
        txtFecha.setValue(selectedPedido.getFecha());
        txtFecha.setDisabled(false);
        txtCodigoUsuario_Usuario.setValue(selectedPedido.getCodigoUsuario_Usuario());
        txtCodigoUsuario_Usuario.setDisabled(false);
        txtCodigoPedido.setValue(selectedPedido.getCodigoPedido());
        txtCodigoPedido.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPedido == null) && (entity == null)) {
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
            entity = new Pedido();

            Long codigoPedido = FacesUtils.checkLong(txtCodigoPedido);

            entity.setCodigoPedido(codigoPedido);
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setUsuario((FacesUtils.checkLong(txtCodigoUsuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtCodigoUsuario_Usuario)) : null);
            businessDelegatorView.savePedido(entity);
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
                Long codigoPedido = new Long(selectedPedido.getCodigoPedido());
                entity = businessDelegatorView.getPedido(codigoPedido);
            }

            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setUsuario((FacesUtils.checkLong(txtCodigoUsuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtCodigoUsuario_Usuario)) : null);
            businessDelegatorView.updatePedido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPedido = (PedidoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPedido"));

            Long codigoPedido = new Long(selectedPedido.getCodigoPedido());
            entity = businessDelegatorView.getPedido(codigoPedido);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoPedido = FacesUtils.checkLong(txtCodigoPedido);
            entity = businessDelegatorView.getPedido(codigoPedido);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePedido(entity);
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
            selectedPedido = (PedidoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPedido"));

            Long codigoPedido = new Long(selectedPedido.getCodigoPedido());
            entity = businessDelegatorView.getPedido(codigoPedido);
            businessDelegatorView.deletePedido(entity);
            data.remove(selectedPedido);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigoPedido, Date fecha,
        Long codigoUsuario_Usuario) throws Exception {
        try {
            entity.setFecha(FacesUtils.checkDate(fecha));
            businessDelegatorView.updatePedido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PedidoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCodigoUsuario_Usuario() {
        return txtCodigoUsuario_Usuario;
    }

    public void setTxtCodigoUsuario_Usuario(InputText txtCodigoUsuario_Usuario) {
        this.txtCodigoUsuario_Usuario = txtCodigoUsuario_Usuario;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public InputText getTxtCodigoPedido() {
        return txtCodigoPedido;
    }

    public void setTxtCodigoPedido(InputText txtCodigoPedido) {
        this.txtCodigoPedido = txtCodigoPedido;
    }

    public List<PedidoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPedido();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PedidoDTO> pedidoDTO) {
        this.data = pedidoDTO;
    }

    public PedidoDTO getSelectedPedido() {
        return selectedPedido;
    }

    public void setSelectedPedido(PedidoDTO pedido) {
        this.selectedPedido = pedido;
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
    
    public String regresar(){

    	return "/login.xhtml";
    
    }
}
