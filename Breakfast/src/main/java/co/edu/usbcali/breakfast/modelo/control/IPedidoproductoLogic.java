package co.edu.usbcali.breakfast.modelo.control;

import co.edu.usbcali.breakfast.modelo.Pedidoproducto;
import co.edu.usbcali.breakfast.modelo.dto.PedidoproductoDTO;

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
public interface IPedidoproductoLogic {
    public List<Pedidoproducto> getPedidoproducto() throws Exception;

    /**
         * Save an new Pedidoproducto entity
         */
    public void savePedidoproducto(Pedidoproducto entity)
        throws Exception;

    /**
         * Delete an existing Pedidoproducto entity
         *
         */
    public void deletePedidoproducto(Pedidoproducto entity)
        throws Exception;

    /**
        * Update an existing Pedidoproducto entity
        *
        */
    public void updatePedidoproducto(Pedidoproducto entity)
        throws Exception;

    /**
         * Load an existing Pedidoproducto entity
         *
         */
    public Pedidoproducto getPedidoproducto(Long codigoPedidoProducto)
        throws Exception;

    public List<Pedidoproducto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Pedidoproducto> findPagePedidoproducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPedidoproducto() throws Exception;

    public List<PedidoproductoDTO> getDataPedidoproducto()
        throws Exception;
}
