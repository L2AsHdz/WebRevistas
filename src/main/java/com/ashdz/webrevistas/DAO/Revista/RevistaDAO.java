package com.ashdz.webrevistas.DAO.Revista;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.Revista;
import java.sql.ResultSet;

/**
 *
 * @author asael
 */
public interface RevistaDAO extends CRUD<Revista>{
    public ResultSet getResultSetRev();
    public ResultSet getResultSetRevAll();
}
