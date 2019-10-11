package com.ashdz.webrevistas.DAO.Comentario;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.Comentario;
import java.sql.ResultSet;

/**
 *
 * @author asael
 */
public interface ComentarioDAO extends CRUD<Comentario>{
    public ResultSet getResultSetComents();
}
