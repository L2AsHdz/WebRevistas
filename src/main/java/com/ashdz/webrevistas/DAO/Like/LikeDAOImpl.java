package com.ashdz.webrevistas.DAO.Like;

import com.ashdz.webrevistas.model.Conexion;
import com.ashdz.webrevistas.model.Like;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asael
 */
public class LikeDAOImpl implements LikeDAO{
    private static LikeDAOImpl likeDAO = null;
    private Connection conexion = Conexion.getConexion();

    private LikeDAOImpl(){}
    
    public static LikeDAOImpl getLikeDAO(){
        if (likeDAO == null) {
            likeDAO = new LikeDAOImpl();
        }
        return likeDAO;
    }

    @Override
    public int getNoLikes(int idR) {
        int no = 0;
        
        try {
            String sql = "SELECT COUNT(IdRevista) FROM LikeRevista WHERE IdRevista=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idR);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                no = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return no;
    }

    @Override
    public List<Like> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Like lk) {
        try {
            String sql = "INSERT INTO LikeRevista VALUES (?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, lk.getIdRevista());
            ps.setString(2, lk.getEmailSuscriptor());
            ps.setString(3, lk.getFechaLike().toString());
            ps.executeUpdate();
            System.out.println("Like registrado Correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se registro el like");
            ex.printStackTrace();
        }
    }

    @Override
    public Like getObject(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Like t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int idR, String email) {
        try {
            String sql = "DELETE FROM LikeRevista WHERE IdRevista=? AND EmailSuscriptor=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idR);
            ps.setString(2, email);
            ps.executeUpdate();
            System.out.println("Like eliminado");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("NO se elimino el registro");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean verificarLike(int idR, String email) {
        boolean bol = false;
        
        try {
            String sql = "SELECT * FROM LikeRevista WHERE IdRevista=? AND EmailSuscriptor=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idR);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                bol = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return bol;
    }
}
