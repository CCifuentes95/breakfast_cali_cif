package co.edu.usbcali.breakfast.modelo.control;

import co.edu.usbcali.breakfast.modelo.Pedido;
import co.edu.usbcali.breakfast.modelo.dto.PedidoDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IPedidoLogic {
    public List<Pedido> getPedido() throws Exception;

    /**
         * Save an new Pedido entity
         */
    public void savePedido(Pedido entity) throws Exception;

    /**
         * Delete an existing Pedido entity
         *
         */
    public void deletePedido(Pedido entity) throws Exception;

    /**
        * Update an existing Pedido entity
        *
        */
    public void updatePedido(Pedido entity) throws Exception;

    /**
         * Load an existing Pedido entity
         *
         */
    public Pedido getPedido(Long codigoPedido) throws Exception;

    public List<Pedido> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pedido> findPagePedido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPedido() throws Exception;

    public List<PedidoDTO> getDataPedido() throws Exception;
}
