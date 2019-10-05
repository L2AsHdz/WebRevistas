package com.ashdz.webrevistas.DAO.Usuario;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.Usuario;

/**
 *
 * @author asael
 */
public interface UsuarioDAO extends CRUD<Usuario>{
    public Usuario getByEmail(Object o);
    public Usuario getByUserName(Object o);
}
