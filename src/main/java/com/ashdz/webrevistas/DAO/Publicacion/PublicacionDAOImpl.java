package com.ashdz.webrevistas.DAO.Publicacion;

import com.ashdz.webrevistas.model.Conexion;
import com.ashdz.webrevistas.model.Publicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author asael
 */
public class PublicacionDAOImpl implements PublicacionDAO{
    private static PublicacionDAOImpl edicionDAO = null;
    private Connection conexion = Conexion.getConexion();

    private PublicacionDAOImpl(){}
    
    public static PublicacionDAOImpl getRevistaDAO(){
        if (edicionDAO == null) {
            edicionDAO = new PublicacionDAOImpl();
        }
        return edicionDAO;
    }

    @Override
    public ResultSet getResultSetEd() {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT * FROM PublicacionRevista";
            Statement declaracion = conexion.createStatement();
            
            rs = declaracion.executeQuery(sql);
            System.out.println("resutset de publicacion obtenido");
            declaracion.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public int getNoEdicionSig(int idR) {
        int noEd = 0;
        
        try {
            String sql = "SELECT COUNT(p.IdPublicacion)+1 FROM PublicacionRevista p WHERE IdRevista = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idR);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                noEd = rs.getInt(1);
            }
            System.out.println("Numero de edicion obtenido");
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return noEd;
    }

    @Override
    public List<Publicacion> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Publicacion e) {
        try{
           String sql = "INSERT INTO PublicacionRevista (IdRevista,EdicionRevista,"
                   + "FechaPublicacion,Archivo) VALUES (?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, e.getIdrevista());
            ps.setInt(2, e.getNoEdicion());
            ps.setString(3, e.getFechaPublicacion().toString());
            ps.setBlob(4, e.getPdf());
            ps.executeUpdate();
            System.out.println("Revista ingresada correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se inserto la revista");
            ex.printStackTrace();
        }
    }

    @Override
    public Publicacion getObject(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Publicacion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
