package com.ashdz.webrevistas.DAO.Suscripcion;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.Suscripcion;
import java.sql.ResultSet;

/**
 *
 * @author asael
 */
public interface SuscripcionDAO extends CRUD<Suscripcion>{
    public ResultSet getResultSetSuscripcion(String email);
}
