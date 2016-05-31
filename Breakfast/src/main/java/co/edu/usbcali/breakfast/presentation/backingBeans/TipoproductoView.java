package co.edu.usbcali.breakfast.presentation.backingBeans;

import co.edu.usbcali.breakfast.exceptions.*;
import co.edu.usbcali.breakfast.modelo.*;
import co.edu.usbcali.breakfast.modelo.dto.TipoproductoDTO;
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
public class TipoproductoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoproductoView.class);
    private InputText txtDescripcion;
    private InputText txtCodigoTipoproducto;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoproductoDTO> data;
    private TipoproductoDTO selectedTipoproducto;
    private Tipoproducto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipoproductoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoproductoDTO tipoproductoDTO = (TipoproductoDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(tipoproductoDTO.getDescripcion());

            if (txtCodigoTipoproducto == null) {
                txtCodigoTipoproducto = new InputText();
            }

            txtCodigoTipoproducto.setValue(tipoproductoDTO.getCodigoTipoproducto());

            Long codigoTipoproducto = FacesUtils.checkLong(txtCodigoTipoproducto);
            entity = businessDelegatorView.getTipoproducto(codigoTipoproducto);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoproducto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoproducto = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtCodigoTipoproducto != null) {
            txtCodigoTipoproducto.setValue(null);
            txtCodigoTipoproducto.setDisabled(false);
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
            Long codigoTipoproducto = FacesUtils.checkLong(txtCodigoTipoproducto);
            entity = (codigoTipoproducto != null)
                ? businessDelegatorView.getTipoproducto(codigoTipoproducto) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtCodigoTipoproducto.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtCodigoTipoproducto.setValue(entity.getCodigoTipoproducto());
            txtCodigoTipoproducto.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoproducto = (TipoproductoDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedTipoproducto"));
        txtDescripcion.setValue(selectedTipoproducto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtCodigoTipoproducto.setValue(selectedTipoproducto.getCodigoTipoproducto());
        txtCodigoTipoproducto.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoproducto == null) && (entity == null)) {
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
            entity = new Tipoproducto();

            Long codigoTipoproducto = FacesUtils.checkLong(txtCodigoTipoproducto);

            entity.setCodigoTipoproducto(codigoTipoproducto);
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            businessDelegatorView.saveTipoproducto(entity);
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
                Long codigoTipoproducto = new Long(selectedTipoproducto.getCodigoTipoproducto());
                entity = businessDelegatorView.getTipoproducto(codigoTipoproducto);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            businessDelegatorView.updateTipoproducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoproducto = (TipoproductoDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedTipoproducto"));

            Long codigoTipoproducto = new Long(selectedTipoproducto.getCodigoTipoproducto());
            entity = businessDelegatorView.getTipoproducto(codigoTipoproducto);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoTipoproducto = FacesUtils.checkLong(txtCodigoTipoproducto);
            entity = businessDelegatorView.getTipoproducto(codigoTipoproducto);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoproducto(entity);
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
            selectedTipoproducto = (TipoproductoDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedTipoproducto"));

            Long codigoTipoproducto = new Long(selectedTipoproducto.getCodigoTipoproducto());
            entity = businessDelegatorView.getTipoproducto(codigoTipoproducto);
            businessDelegatorView.deleteTipoproducto(entity);
            data.remove(selectedTipoproducto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigoTipoproducto,
        String descripcion) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            businessDelegatorView.updateTipoproducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoproductoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtCodigoTipoproducto() {
        return txtCodigoTipoproducto;
    }

    public void setTxtCodigoTipoproducto(InputText txtCodigoTipoproducto) {
        this.txtCodigoTipoproducto = txtCodigoTipoproducto;
    }

    public List<TipoproductoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoproducto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoproductoDTO> tipoproductoDTO) {
        this.data = tipoproductoDTO;
    }

    public TipoproductoDTO getSelectedTipoproducto() {
        return selectedTipoproducto;
    }

    public void setSelectedTipoproducto(TipoproductoDTO tipoproducto) {
        this.selectedTipoproducto = tipoproducto;
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
