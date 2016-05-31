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
import co.edu.usbcali.breakfast.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IPedidoLogic pedidoLogic;
    @Autowired
    private IPedidoproductoLogic pedidoproductoLogic;
    @Autowired
    private IProductoLogic productoLogic;
    @Autowired
    private ITipoproductoLogic tipoproductoLogic;
    @Autowired
    private IUsuarioLogic usuarioLogic;

    public List<Pedido> getPedido() throws Exception {
        return pedidoLogic.getPedido();
    }

    public void savePedido(Pedido entity) throws Exception {
        pedidoLogic.savePedido(entity);
    }

    public void deletePedido(Pedido entity) throws Exception {
        pedidoLogic.deletePedido(entity);
    }

    public void updatePedido(Pedido entity) throws Exception {
        pedidoLogic.updatePedido(entity);
    }

    public Pedido getPedido(Long codigoPedido) throws Exception {
        Pedido pedido = null;

        try {
            pedido = pedidoLogic.getPedido(codigoPedido);
        } catch (Exception e) {
            throw e;
        }

        return pedido;
    }

    public List<Pedido> findByCriteriaInPedido(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return pedidoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Pedido> findPagePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return pedidoLogic.findPagePedido(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberPedido() throws Exception {
        return pedidoLogic.findTotalNumberPedido();
    }

    public List<PedidoDTO> getDataPedido() throws Exception {
        return pedidoLogic.getDataPedido();
    }

    public List<Pedidoproducto> getPedidoproducto() throws Exception {
        return pedidoproductoLogic.getPedidoproducto();
    }

    public void savePedidoproducto(Pedidoproducto entity)
        throws Exception {
        pedidoproductoLogic.savePedidoproducto(entity);
    }

    public void deletePedidoproducto(Pedidoproducto entity)
        throws Exception {
        pedidoproductoLogic.deletePedidoproducto(entity);
    }

    public void updatePedidoproducto(Pedidoproducto entity)
        throws Exception {
        pedidoproductoLogic.updatePedidoproducto(entity);
    }

    public Pedidoproducto getPedidoproducto(Long codigoPedidoProducto)
        throws Exception {
        Pedidoproducto pedidoproducto = null;

        try {
            pedidoproducto = pedidoproductoLogic.getPedidoproducto(codigoPedidoProducto);
        } catch (Exception e) {
            throw e;
        }

        return pedidoproducto;
    }

    public List<Pedidoproducto> findByCriteriaInPedidoproducto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return pedidoproductoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Pedidoproducto> findPagePedidoproducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return pedidoproductoLogic.findPagePedidoproducto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberPedidoproducto() throws Exception {
        return pedidoproductoLogic.findTotalNumberPedidoproducto();
    }

    public List<PedidoproductoDTO> getDataPedidoproducto()
        throws Exception {
        return pedidoproductoLogic.getDataPedidoproducto();
    }

    public List<Producto> getProducto() throws Exception {
        return productoLogic.getProducto();
    }

    public void saveProducto(Producto entity) throws Exception {
        productoLogic.saveProducto(entity);
    }

    public void deleteProducto(Producto entity) throws Exception {
        productoLogic.deleteProducto(entity);
    }

    public void updateProducto(Producto entity) throws Exception {
        productoLogic.updateProducto(entity);
    }

    public Producto getProducto(Long codigoProducto) throws Exception {
        Producto producto = null;

        try {
            producto = productoLogic.getProducto(codigoProducto);
        } catch (Exception e) {
            throw e;
        }

        return producto;
    }

    public List<Producto> findByCriteriaInProducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return productoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Producto> findPageProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return productoLogic.findPageProducto(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberProducto() throws Exception {
        return productoLogic.findTotalNumberProducto();
    }

    public List<ProductoDTO> getDataProducto() throws Exception {
        return productoLogic.getDataProducto();
    }

    public List<Tipoproducto> getTipoproducto() throws Exception {
        return tipoproductoLogic.getTipoproducto();
    }

    public void saveTipoproducto(Tipoproducto entity) throws Exception {
        tipoproductoLogic.saveTipoproducto(entity);
    }

    public void deleteTipoproducto(Tipoproducto entity)
        throws Exception {
        tipoproductoLogic.deleteTipoproducto(entity);
    }

    public void updateTipoproducto(Tipoproducto entity)
        throws Exception {
        tipoproductoLogic.updateTipoproducto(entity);
    }

    public Tipoproducto getTipoproducto(Long codigoTipoproducto)
        throws Exception {
        Tipoproducto tipoproducto = null;

        try {
            tipoproducto = tipoproductoLogic.getTipoproducto(codigoTipoproducto);
        } catch (Exception e) {
            throw e;
        }

        return tipoproducto;
    }

    public List<Tipoproducto> findByCriteriaInTipoproducto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return tipoproductoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Tipoproducto> findPageTipoproducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tipoproductoLogic.findPageTipoproducto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTipoproducto() throws Exception {
        return tipoproductoLogic.findTotalNumberTipoproducto();
    }

    public List<TipoproductoDTO> getDataTipoproducto()
        throws Exception {
        return tipoproductoLogic.getDataTipoproducto();
    }

    public List<Usuario> getUsuario() throws Exception {
        return usuarioLogic.getUsuario();
    }

    public void saveUsuario(Usuario entity) throws Exception {
        usuarioLogic.saveUsuario(entity);
    }

    public void deleteUsuario(Usuario entity) throws Exception {
        usuarioLogic.deleteUsuario(entity);
    }

    public void updateUsuario(Usuario entity) throws Exception {
        usuarioLogic.updateUsuario(entity);
    }

    public Usuario getUsuario(Long codigoUsuario) throws Exception {
        Usuario usuario = null;

        try {
            usuario = usuarioLogic.getUsuario(codigoUsuario);
        } catch (Exception e) {
            throw e;
        }

        return usuario;
    }

    public List<Usuario> findByCriteriaInUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuario> findPageUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuarioLogic.findPageUsuario(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuario() throws Exception {
        return usuarioLogic.findTotalNumberUsuario();
    }

    public List<UsuarioDTO> getDataUsuario() throws Exception {
        return usuarioLogic.getDataUsuario();
    }
}
