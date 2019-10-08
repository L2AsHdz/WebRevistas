package com.ashdz.webrevistas.DAO.EtiquetadoDAO;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.Etiquetado;

/**
 *
 * @author asael
 */
public interface EtiquetadoDAO extends CRUD<Etiquetado>{
    public void createEtiquetado(int idE, int idR);
}
