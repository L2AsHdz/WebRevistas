package com.ashdz.webrevistas.DAO.Categoria;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.Categoria;
import java.sql.ResultSet;

/**
 *
 * @author asael
 */
public interface CategoriaDAO extends CRUD<Categoria>{
    public ResultSet getResultSet();
    public void createCat(String c);
}
