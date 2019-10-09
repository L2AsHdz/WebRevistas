package com.ashdz.webrevistas.DAO.Suscripcion;

import com.ashdz.webrevistas.model.Conexion;
import com.ashdz.webrevistas.model.Suscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asael
 */
public class SuscripcionDAOImpl implements SuscripcionDAO{
    private static SuscripcionDAOImpl sucripDAO = null;
    private Connection conexion = Conexion.getConexion();

    private SuscripcionDAOImpl(){}
    
    public static SuscripcionDAOImpl getSuscripcionDAO(){
        if (sucripDAO == null) {
            sucripDAO = new SuscripcionDAOImpl();
        }
        return sucripDAO;
    }

    @Override
    public List<Suscripcion> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Suscripcion s) {
        try{
           String sql = "INSERT INTO SuscripcionRevista (IdRevista,EmailSuscriptor,"
                   + "FechaSuscripcion) VALUES (?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, s.getIdRevista());
            ps.setString(2, s.getEmailSuscriptor());
            ps.setString(3, s.getFechaSuscripcion().toString());
            ps.executeUpdate();
            System.out.println("Suscripcion ingresada correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se inserto la suscripcion");
            ex.printStackTrace();
        }
    }

    @Override
    public Suscripcion getObject(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Suscripcion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getResultSetSuscripcion(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
