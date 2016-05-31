package co.edu.usbcali.breakfast.modelo.control;

import co.edu.usbcali.breakfast.modelo.Tipoproducto;
import co.edu.usbcali.breakfast.modelo.dto.TipoproductoDTO;

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
public interface ITipoproductoLogic {
    public List<Tipoproducto> getTipoproducto() throws Exception;

    /**
         * Save an new Tipoproducto entity
         */
    public void saveTipoproducto(Tipoproducto entity) throws Exception;

    /**
         * Delete an existing Tipoproducto entity
         *
         */
    public void deleteTipoproducto(Tipoproducto entity)
        throws Exception;

    /**
        * Update an existing Tipoproducto entity
        *
        */
    public void updateTipoproducto(Tipoproducto entity)
        throws Exception;

    /**
         * Load an existing Tipoproducto entity
         *
         */
    public Tipoproducto getTipoproducto(Long codigoTipoproducto)
        throws Exception;

    public List<Tipoproducto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Tipoproducto> findPageTipoproducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoproducto() throws Exception;

    public List<TipoproductoDTO> getDataTipoproducto()
        throws Exception;
}
