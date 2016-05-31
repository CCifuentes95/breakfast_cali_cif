package co.edu.usbcali.breakfast.presentation.backingBeans;

import co.edu.usbcali.breakfast.exceptions.*;
import co.edu.usbcali.breakfast.modelo.*;
import co.edu.usbcali.breakfast.modelo.dto.ProductoDTO;
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
public class ProductoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProductoView.class);
    private InputText txtDescripcion;
    private InputText txtImagen;
    private InputText txtNombre;
    private InputText txtPrecio;
    private InputText txtCodigoTipoproducto_Tipoproducto;
    private InputText txtCodigoProducto;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProductoDTO> data;
    private ProductoDTO selectedProducto;
    private Producto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ProductoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ProductoDTO productoDTO = (ProductoDTO) e.getObject();

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(productoDTO.getDescripcion());

            if (txtImagen == null) {
                txtImagen = new InputText();
            }

            txtImagen.setValue(productoDTO.getImagen());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(productoDTO.getNombre());

            if (txtPrecio == null) {
                txtPrecio = new InputText();
            }

            txtPrecio.setValue(productoDTO.getPrecio());

            if (txtCodigoTipoproducto_Tipoproducto == null) {
                txtCodigoTipoproducto_Tipoproducto = new InputText();
            }

            txtCodigoTipoproducto_Tipoproducto.setValue(productoDTO.getCodigoTipoproducto_Tipoproducto());

            if (txtCodigoProducto == null) {
                txtCodigoProducto = new InputText();
            }

            txtCodigoProducto.setValue(productoDTO.getCodigoProducto());

            Long codigoProducto = FacesUtils.checkLong(txtCodigoProducto);
            entity = businessDelegatorView.getProducto(codigoProducto);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedProducto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProducto = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtImagen != null) {
            txtImagen.setValue(null);
            txtImagen.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtPrecio != null) {
            txtPrecio.setValue(null);
            txtPrecio.setDisabled(true);
        }

        if (txtCodigoTipoproducto_Tipoproducto != null) {
            txtCodigoTipoproducto_Tipoproducto.setValue(null);
            txtCodigoTipoproducto_Tipoproducto.setDisabled(true);
        }

        if (txtCodigoProducto != null) {
            txtCodigoProducto.setValue(null);
            txtCodigoProducto.setDisabled(false);
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
            Long codigoProducto = FacesUtils.checkLong(txtCodigoProducto);
            entity = (codigoProducto != null)
                ? businessDelegatorView.getProducto(codigoProducto) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtImagen.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPrecio.setDisabled(false);
            txtCodigoTipoproducto_Tipoproducto.setDisabled(false);
            txtCodigoProducto.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtImagen.setValue(entity.getImagen());
            txtImagen.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPrecio.setValue(entity.getPrecio());
            txtPrecio.setDisabled(false);
            txtCodigoTipoproducto_Tipoproducto.setValue(entity.getTipoproducto()
                                                              .getCodigoTipoproducto());
            txtCodigoTipoproducto_Tipoproducto.setDisabled(false);
            txtCodigoProducto.setValue(entity.getCodigoProducto());
            txtCodigoProducto.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedProducto = (ProductoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedProducto"));
        txtDescripcion.setValue(selectedProducto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtImagen.setValue(selectedProducto.getImagen());
        txtImagen.setDisabled(false);
        txtNombre.setValue(selectedProducto.getNombre());
        txtNombre.setDisabled(false);
        txtPrecio.setValue(selectedProducto.getPrecio());
        txtPrecio.setDisabled(false);
        txtCodigoTipoproducto_Tipoproducto.setValue(selectedProducto.getCodigoTipoproducto_Tipoproducto());
        txtCodigoTipoproducto_Tipoproducto.setDisabled(false);
        txtCodigoProducto.setValue(selectedProducto.getCodigoProducto());
        txtCodigoProducto.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProducto == null) && (entity == null)) {
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
            entity = new Producto();

            Long codigoProducto = FacesUtils.checkLong(txtCodigoProducto);

            entity.setCodigoProducto(codigoProducto);
            entity.setDescripcion(FacesUtils.checkLong(txtDescripcion));
            entity.setImagen(FacesUtils.checkString(txtImagen));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrecio(FacesUtils.checkLong(txtPrecio));
            entity.setTipoproducto((FacesUtils.checkLong(
                    txtCodigoTipoproducto_Tipoproducto) != null)
                ? businessDelegatorView.getTipoproducto(FacesUtils.checkLong(
                        txtCodigoTipoproducto_Tipoproducto)) : null);
            businessDelegatorView.saveProducto(entity);
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
                Long codigoProducto = new Long(selectedProducto.getCodigoProducto());
                entity = businessDelegatorView.getProducto(codigoProducto);
            }

            entity.setDescripcion(FacesUtils.checkLong(txtDescripcion));
            entity.setImagen(FacesUtils.checkString(txtImagen));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrecio(FacesUtils.checkLong(txtPrecio));
            entity.setTipoproducto((FacesUtils.checkLong(
                    txtCodigoTipoproducto_Tipoproducto) != null)
                ? businessDelegatorView.getTipoproducto(FacesUtils.checkLong(
                        txtCodigoTipoproducto_Tipoproducto)) : null);
            businessDelegatorView.updateProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProducto = (ProductoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedProducto"));

            Long codigoProducto = new Long(selectedProducto.getCodigoProducto());
            entity = businessDelegatorView.getProducto(codigoProducto);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoProducto = FacesUtils.checkLong(txtCodigoProducto);
            entity = businessDelegatorView.getProducto(codigoProducto);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProducto(entity);
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
            selectedProducto = (ProductoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedProducto"));

            Long codigoProducto = new Long(selectedProducto.getCodigoProducto());
            entity = businessDelegatorView.getProducto(codigoProducto);
            businessDelegatorView.deleteProducto(entity);
            data.remove(selectedProducto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigoProducto, Long descripcion,
        String imagen, String nombre, Long precio,
        Long codigoTipoproducto_Tipoproducto) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkLong(descripcion));
            entity.setImagen(FacesUtils.checkString(imagen));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPrecio(FacesUtils.checkLong(precio));
            businessDelegatorView.updateProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProductoView").requestRender();
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

    public InputText getTxtImagen() {
        return txtImagen;
    }

    public void setTxtImagen(InputText txtImagen) {
        this.txtImagen = txtImagen;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(InputText txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public InputText getTxtCodigoTipoproducto_Tipoproducto() {
        return txtCodigoTipoproducto_Tipoproducto;
    }

    public void setTxtCodigoTipoproducto_Tipoproducto(
        InputText txtCodigoTipoproducto_Tipoproducto) {
        this.txtCodigoTipoproducto_Tipoproducto = txtCodigoTipoproducto_Tipoproducto;
    }

    public InputText getTxtCodigoProducto() {
        return txtCodigoProducto;
    }

    public void setTxtCodigoProducto(InputText txtCodigoProducto) {
        this.txtCodigoProducto = txtCodigoProducto;
    }

    public List<ProductoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataProducto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ProductoDTO> productoDTO) {
        this.data = productoDTO;
    }

    public ProductoDTO getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoDTO producto) {
        this.selectedProducto = producto;
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
