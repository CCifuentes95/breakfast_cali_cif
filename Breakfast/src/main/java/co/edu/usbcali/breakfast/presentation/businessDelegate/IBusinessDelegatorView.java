package co.edu.usbcali.breakfast.presentation.businessDelegate;

import co.edu.usbcali.breakfast.modelo.Pedido;
import co.edu.usbcali.breakfast.modelo.Pedidoproducto;
import co.edu.usbcali.breakfast.modelo.Producto;
import co.edu.usbcali.breakfast.modelo.Tipoproducto;
import co.edu.usbcali.breakfast.modelo.Usuario;
import co.edu.usbcali.breakfast.modelo.control.IPedidoLogic;
import co.edu.usbcali.breakfast.modelo.control.IPedidoproductoLogic;
import co.edu.usbcali.breakfast.modelo.control.IProductoLogic;
import co.edu.usbcali.breakfast.modelo.control.ITipoproductoLogic;
import co.edu.usbcali.breakfast.modelo.control.IUsuarioLogic;
import co.edu.usbcali.breakfast.modelo.control.PedidoLogic;
import co.edu.usbcali.breakfast.modelo.control.PedidoproductoLogic;
import co.edu.usbcali.breakfast.modelo.control.ProductoLogic;
import co.edu.usbcali.breakfast.modelo.control.TipoproductoLogic;
import co.edu.usbcali.breakfast.modelo.control.UsuarioLogic;
import co.edu.usbcali.breakfast.modelo.dto.PedidoDTO;
import co.edu.usbcali.breakfast.modelo.dto.PedidoproductoDTO;
import co.edu.usbcali.breakfast.modelo.dto.ProductoDTO;
import co.edu.usbcali.breakfast.modelo.dto.TipoproductoDTO;
import co.edu.usbcali.breakfast.modelo.dto.UsuarioDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Pedido> getPedido() throws Exception;

    public void savePedido(Pedido entity) throws Exception;

    public void deletePedido(Pedido entity) throws Exception;

    public void updatePedido(Pedido entity) throws Exception;

    public Pedido getPedido(Long codigoPedido) throws Exception;

    public List<Pedido> findByCriteriaInPedido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pedido> findPagePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPedido() throws Exception;

    public List<PedidoDTO> getDataPedido() throws Exception;

    public List<Pedidoproducto> getPedidoproducto() throws Exception;

    public void savePedidoproducto(Pedidoproducto entity)
        throws Exception;

    public void deletePedidoproducto(Pedidoproducto entity)
        throws Exception;

    public void updatePedidoproducto(Pedidoproducto entity)
        throws Exception;

    public Pedidoproducto getPedidoproducto(Long codigoPedidoProducto)
        throws Exception;

    public List<Pedidoproducto> findByCriteriaInPedidoproducto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<Pedidoproducto> findPagePedidoproducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPedidoproducto() throws Exception;

    public List<PedidoproductoDTO> getDataPedidoproducto()
        throws Exception;

    public List<Producto> getProducto() throws Exception;

    public void saveProducto(Producto entity) throws Exception;

    public void deleteProducto(Producto entity) throws Exception;

    public void updateProducto(Producto entity) throws Exception;

    public Producto getProducto(Long codigoProducto) throws Exception;

    public List<Producto> findByCriteriaInProducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Producto> findPageProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberProducto() throws Exception;

    public List<ProductoDTO> getDataProducto() throws Exception;

    public List<Tipoproducto> getTipoproducto() throws Exception;

    public void saveTipoproducto(Tipoproducto entity) throws Exception;

    public void deleteTipoproducto(Tipoproducto entity)
        throws Exception;

    public void updateTipoproducto(Tipoproducto entity)
        throws Exception;

    public Tipoproducto getTipoproducto(Long codigoTipoproducto)
        throws Exception;

    public List<Tipoproducto> findByCriteriaInTipoproducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tipoproducto> findPageTipoproducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoproducto() throws Exception;

    public List<TipoproductoDTO> getDataTipoproducto()
        throws Exception;

    public List<Usuario> getUsuario() throws Exception;

    public void saveUsuario(Usuario entity) throws Exception;

    public void deleteUsuario(Usuario entity) throws Exception;

    public void updateUsuario(Usuario entity) throws Exception;

    public Usuario getUsuario(Long codigoUsuario) throws Exception;

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuario() throws Exception;

    public List<UsuarioDTO> getDataUsuario() throws Exception;
}
