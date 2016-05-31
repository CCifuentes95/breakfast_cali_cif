package co.edu.usbcali.breakfast.modelo.control;

import co.edu.usbcali.breakfast.dataaccess.dao.*;
import co.edu.usbcali.breakfast.exceptions.*;
import co.edu.usbcali.breakfast.modelo.*;
import co.edu.usbcali.breakfast.modelo.dto.ProductoDTO;
import co.edu.usbcali.breakfast.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("ProductoLogic")
public class ProductoLogic implements IProductoLogic {
    private static final Logger log = LoggerFactory.getLogger(ProductoLogic.class);

    /**
     * DAO injected by Spring that manages Producto entities
     *
     */
    @Autowired
    private IProductoDAO productoDAO;

    /**
    * DAO injected by Spring that manages Pedidoproducto entities
    *
    */
    @Autowired
    private IPedidoproductoDAO pedidoproductoDAO;

    /**
    * Logic injected by Spring that manages Tipoproducto entities
    *
    */
    @Autowired
    ITipoproductoLogic logicTipoproducto1;

    @Transactional(readOnly = true)
    public List<Producto> getProducto() throws Exception {
        log.debug("finding all Producto instances");

        List<Producto> list = new ArrayList<Producto>();

        try {
            list = productoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Producto failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Producto");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveProducto(Producto entity) throws Exception {
        log.debug("saving Producto instance");

        try {
            if (entity.getTipoproducto() == null) {
                throw new ZMessManager().new ForeignException("tipoproducto");
            }

//            if (entity.getCodigoProducto() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "codigoProducto");
//            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getImagen() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getImagen(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("imagen");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getPrecio() == null) {
                throw new ZMessManager().new EmptyFieldException("precio");
            }

            if ((entity.getPrecio() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getPrecio(), 131089, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("precio");
            }

            if (entity.getTipoproducto().getCodigoTipoproducto() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoTipoproducto_Tipoproducto");
            }

//            if (getProducto(entity.getCodigoProducto()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            productoDAO.save(entity);

            log.debug("save Producto successful");
        } catch (Exception e) {
            log.error("save Producto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteProducto(Producto entity) throws Exception {
        log.debug("deleting Producto instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Producto");
        }

        if (entity.getCodigoProducto() == null) {
            throw new ZMessManager().new EmptyFieldException("codigoProducto");
        }

        List<Pedidoproducto> pedidoproductos = null;

        try {
            pedidoproductos = pedidoproductoDAO.findByProperty("producto.codigoProducto",
                    entity.getCodigoProducto());

            if (Utilities.validationsList(pedidoproductos) == true) {
                throw new ZMessManager().new DeletingException(
                    "pedidoproductos");
            }

            productoDAO.delete(entity);

            log.debug("delete Producto successful");
        } catch (Exception e) {
            log.error("delete Producto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateProducto(Producto entity) throws Exception {
        log.debug("updating Producto instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Producto");
            }

            if (entity.getTipoproducto() == null) {
                throw new ZMessManager().new ForeignException("tipoproducto");
            }

            if (entity.getCodigoProducto() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoProducto");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getImagen() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getImagen(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("imagen");
            }

            if (entity.getNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("nombre");
            }

            if ((entity.getNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getNombre(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombre");
            }

            if (entity.getPrecio() == null) {
                throw new ZMessManager().new EmptyFieldException("precio");
            }

            if ((entity.getPrecio() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getPrecio(), 131089, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("precio");
            }

            if (entity.getTipoproducto().getCodigoTipoproducto() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "codigoTipoproducto_Tipoproducto");
            }

            productoDAO.update(entity);

            log.debug("update Producto successful");
        } catch (Exception e) {
            log.error("update Producto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> getDataProducto() throws Exception {
        try {
            List<Producto> producto = productoDAO.findAll();

            List<ProductoDTO> productoDTO = new ArrayList<ProductoDTO>();

            for (Producto productoTmp : producto) {
                ProductoDTO productoDTO2 = new ProductoDTO();

                productoDTO2.setCodigoProducto(productoTmp.getCodigoProducto());
                productoDTO2.setDescripcion((productoTmp.getDescripcion() != null)
                    ? productoTmp.getDescripcion() : null);
                productoDTO2.setImagen((productoTmp.getImagen() != null)
                    ? productoTmp.getImagen() : null);
                productoDTO2.setNombre((productoTmp.getNombre() != null)
                    ? productoTmp.getNombre() : null);
                productoDTO2.setPrecio((productoTmp.getPrecio() != null)
                    ? productoTmp.getPrecio() : null);
                productoDTO2.setCodigoTipoproducto_Tipoproducto((productoTmp.getTipoproducto()
                                                                            .getCodigoTipoproducto() != null)
                    ? productoTmp.getTipoproducto().getCodigoTipoproducto() : null);
                productoDTO.add(productoDTO2);
            }

            return productoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Producto getProducto(Long codigoProducto) throws Exception {
        log.debug("getting Producto instance");

        Producto entity = null;

        try {
            entity = productoDAO.findById(codigoProducto);
        } catch (Exception e) {
            log.error("get Producto failed", e);
            throw new ZMessManager().new FindingException("Producto");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Producto> findPageProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Producto> entity = null;

        try {
            entity = productoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Producto Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberProducto() throws Exception {
        Long entity = null;

        try {
            entity = productoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Producto Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Producto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Producto> list = new ArrayList<Producto>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = productoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
