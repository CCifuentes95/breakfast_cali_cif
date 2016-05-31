package co.edu.usbcali.breakfast.presentation.backingBeans;

import co.edu.usbcali.breakfast.exceptions.*;
import co.edu.usbcali.breakfast.modelo.*;
import co.edu.usbcali.breakfast.modelo.dto.PedidoproductoDTO;
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
public class PedidoproductoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PedidoproductoView.class);
    private InputText txtCodigoTipoproducto;
    private InputText txtCodigoUsuario;
    private InputText txtCodigoPedido_Pedido;
    private InputText txtCodigoProducto_Producto;
    private InputText txtCodigoPedidoProducto;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PedidoproductoDTO> data;
    private PedidoproductoDTO selectedPedidoproducto;
    private Pedidoproducto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PedidoproductoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PedidoproductoDTO pedidoproductoDTO = (PedidoproductoDTO) e.getObject();

            if (txtCodigoTipoproducto == null) {
                txtCodigoTipoproducto = new InputText();
            }

            txtCodigoTipoproducto.setValue(pedidoproductoDTO.getCodigoTipoproducto());

            if (txtCodigoUsuario == null) {
                txtCodigoUsuario = new InputText();
            }

            txtCodigoUsuario.setValue(pedidoproductoDTO.getCodigoUsuario());

            if (txtCodigoPedido_Pedido == null) {
                txtCodigoPedido_Pedido = new InputText();
            }

            txtCodigoPedido_Pedido.setValue(pedidoproductoDTO.getCodigoPedido_Pedido());

            if (txtCodigoProducto_Producto == null) {
                txtCodigoProducto_Producto = new InputText();
            }

            txtCodigoProducto_Producto.setValue(pedidoproductoDTO.getCodigoProducto_Producto());

            if (txtCodigoPedidoProducto == null) {
                txtCodigoPedidoProducto = new InputText();
            }

            txtCodigoPedidoProducto.setValue(pedidoproductoDTO.getCodigoPedidoProducto());

            Long codigoPedidoProducto = FacesUtils.checkLong(txtCodigoPedidoProducto);
            entity = businessDelegatorView.getPedidoproducto(codigoPedidoProducto);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPedidoproducto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPedidoproducto = null;

        if (txtCodigoTipoproducto != null) {
            txtCodigoTipoproducto.setValue(null);
            txtCodigoTipoproducto.setDisabled(true);
        }

        if (txtCodigoUsuario != null) {
            txtCodigoUsuario.setValue(null);
            txtCodigoUsuario.setDisabled(true);
        }

        if (txtCodigoPedido_Pedido != null) {
            txtCodigoPedido_Pedido.setValue(null);
            txtCodigoPedido_Pedido.setDisabled(true);
        }

        if (txtCodigoProducto_Producto != null) {
            txtCodigoProducto_Producto.setValue(null);
            txtCodigoProducto_Producto.setDisabled(true);
        }

        if (txtCodigoPedidoProducto != null) {
            txtCodigoPedidoProducto.setValue(null);
            txtCodigoPedidoProducto.setDisabled(false);
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
            Long codigoPedidoProducto = FacesUtils.checkLong(txtCodigoPedidoProducto);
            entity = (codigoPedidoProducto != null)
                ? businessDelegatorView.getPedidoproducto(codigoPedidoProducto)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigoTipoproducto.setDisabled(false);
            txtCodigoUsuario.setDisabled(false);
            txtCodigoPedido_Pedido.setDisabled(false);
            txtCodigoProducto_Producto.setDisabled(false);
            txtCodigoPedidoProducto.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCodigoTipoproducto.setValue(entity.getCodigoTipoproducto());
            txtCodigoTipoproducto.setDisabled(false);
            txtCodigoUsuario.setValue(entity.getCodigoUsuario());
            txtCodigoUsuario.setDisabled(false);
            txtCodigoPedido_Pedido.setValue(entity.getPedido().getCodigoPedido());
            txtCodigoPedido_Pedido.setDisabled(false);
            txtCodigoProducto_Producto.setValue(entity.getProducto()
                                                      .getCodigoProducto());
            txtCodigoProducto_Producto.setDisabled(false);
            txtCodigoPedidoProducto.setValue(entity.getCodigoPedidoProducto());
            txtCodigoPedidoProducto.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPedidoproducto = (PedidoproductoDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPedidoproducto"));
        txtCodigoTipoproducto.setValue(selectedPedidoproducto.getCodigoTipoproducto());
        txtCodigoTipoproducto.setDisabled(false);
        txtCodigoUsuario.setValue(selectedPedidoproducto.getCodigoUsuario());
        txtCodigoUsuario.setDisabled(false);
        txtCodigoPedido_Pedido.setValue(selectedPedidoproducto.getCodigoPedido_Pedido());
        txtCodigoPedido_Pedido.setDisabled(false);
        txtCodigoProducto_Producto.setValue(selectedPedidoproducto.getCodigoProducto_Producto());
        txtCodigoProducto_Producto.setDisabled(false);
        txtCodigoPedidoProducto.setValue(selectedPedidoproducto.getCodigoPedidoProducto());
        txtCodigoPedidoProducto.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPedidoproducto == null) && (entity == null)) {
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
            entity = new Pedidoproducto();

            Long codigoPedidoProducto = FacesUtils.checkLong(txtCodigoPedidoProducto);

            entity.setCodigoPedidoProducto(codigoPedidoProducto);
            entity.setCodigoTipoproducto(FacesUtils.checkLong(
                    txtCodigoTipoproducto));
            entity.setCodigoUsuario(FacesUtils.checkLong(txtCodigoUsuario));
            entity.setPedido((FacesUtils.checkLong(txtCodigoPedido_Pedido) != null)
                ? businessDelegatorView.getPedido(FacesUtils.checkLong(
                        txtCodigoPedido_Pedido)) : null);
            entity.setProducto((FacesUtils.checkLong(txtCodigoProducto_Producto) != null)
                ? businessDelegatorView.getProducto(FacesUtils.checkLong(
                        txtCodigoProducto_Producto)) : null);
            businessDelegatorView.savePedidoproducto(entity);
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
                Long codigoPedidoProducto = new Long(selectedPedidoproducto.getCodigoPedidoProducto());
                entity = businessDelegatorView.getPedidoproducto(codigoPedidoProducto);
            }

            entity.setCodigoTipoproducto(FacesUtils.checkLong(
                    txtCodigoTipoproducto));
            entity.setCodigoUsuario(FacesUtils.checkLong(txtCodigoUsuario));
            entity.setPedido((FacesUtils.checkLong(txtCodigoPedido_Pedido) != null)
                ? businessDelegatorView.getPedido(FacesUtils.checkLong(
                        txtCodigoPedido_Pedido)) : null);
            entity.setProducto((FacesUtils.checkLong(txtCodigoProducto_Producto) != null)
                ? businessDelegatorView.getProducto(FacesUtils.checkLong(
                        txtCodigoProducto_Producto)) : null);
            businessDelegatorView.updatePedidoproducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPedidoproducto = (PedidoproductoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPedidoproducto"));

            Long codigoPedidoProducto = new Long(selectedPedidoproducto.getCodigoPedidoProducto());
            entity = businessDelegatorView.getPedidoproducto(codigoPedidoProducto);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long codigoPedidoProducto = FacesUtils.checkLong(txtCodigoPedidoProducto);
            entity = businessDelegatorView.getPedidoproducto(codigoPedidoProducto);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePedidoproducto(entity);
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
            selectedPedidoproducto = (PedidoproductoDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedPedidoproducto"));

            Long codigoPedidoProducto = new Long(selectedPedidoproducto.getCodigoPedidoProducto());
            entity = businessDelegatorView.getPedidoproducto(codigoPedidoProducto);
            businessDelegatorView.deletePedidoproducto(entity);
            data.remove(selectedPedidoproducto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long codigoPedidoProducto,
        Long codigoTipoproducto, Long codigoUsuario, Long codigoPedido_Pedido,
        Long codigoProducto_Producto) throws Exception {
        try {
            entity.setCodigoTipoproducto(FacesUtils.checkLong(
                    codigoTipoproducto));
            entity.setCodigoUsuario(FacesUtils.checkLong(codigoUsuario));
            businessDelegatorView.updatePedidoproducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PedidoproductoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCodigoTipoproducto() {
        return txtCodigoTipoproducto;
    }

    public void setTxtCodigoTipoproducto(InputText txtCodigoTipoproducto) {
        this.txtCodigoTipoproducto = txtCodigoTipoproducto;
    }

    public InputText getTxtCodigoUsuario() {
        return txtCodigoUsuario;
    }

    public void setTxtCodigoUsuario(InputText txtCodigoUsuario) {
        this.txtCodigoUsuario = txtCodigoUsuario;
    }

    public InputText getTxtCodigoPedido_Pedido() {
        return txtCodigoPedido_Pedido;
    }

    public void setTxtCodigoPedido_Pedido(InputText txtCodigoPedido_Pedido) {
        this.txtCodigoPedido_Pedido = txtCodigoPedido_Pedido;
    }

    public InputText getTxtCodigoProducto_Producto() {
        return txtCodigoProducto_Producto;
    }

    public void setTxtCodigoProducto_Producto(
        InputText txtCodigoProducto_Producto) {
        this.txtCodigoProducto_Producto = txtCodigoProducto_Producto;
    }

    public InputText getTxtCodigoPedidoProducto() {
        return txtCodigoPedidoProducto;
    }

    public void setTxtCodigoPedidoProducto(InputText txtCodigoPedidoProducto) {
        this.txtCodigoPedidoProducto = txtCodigoPedidoProducto;
    }

    public List<PedidoproductoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPedidoproducto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PedidoproductoDTO> pedidoproductoDTO) {
        this.data = pedidoproductoDTO;
    }

    public PedidoproductoDTO getSelectedPedidoproducto() {
        return selectedPedidoproducto;
    }

    public void setSelectedPedidoproducto(PedidoproductoDTO pedidoproducto) {
        this.selectedPedidoproducto = pedidoproducto;
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
