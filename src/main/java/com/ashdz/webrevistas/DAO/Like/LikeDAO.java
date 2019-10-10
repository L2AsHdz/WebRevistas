package com.ashdz.webrevistas.DAO.Like;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.model.Like;

/**
 *
 * @author asael
 */
public interface LikeDAO extends CRUD<Like>{
    public int getNoLikes(int idR);
    public void delete(int idR, String email);
    public boolean verificarLike(int idR, String email);
    
}
