package com.ashdz.webrevistas.DAO.Publicacion;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.Publicacion;
import java.sql.ResultSet;

/**
 *
 * @author asael
 */
public interface PublicacionDAO extends CRUD<Publicacion>{
    public ResultSet getResultSetEd();
    public int getNoEdicionSig(int idR);
}
