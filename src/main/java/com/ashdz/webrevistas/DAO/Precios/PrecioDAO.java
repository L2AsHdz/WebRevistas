package com.ashdz.webrevistas.DAO.Precios;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.PrecioGlobal;

/**
 *
 * @author asael
 */
public interface PrecioDAO extends CRUD<PrecioGlobal>{
    public void updateValor(int id, float valor);
    public float getPrecio(int id);
}
