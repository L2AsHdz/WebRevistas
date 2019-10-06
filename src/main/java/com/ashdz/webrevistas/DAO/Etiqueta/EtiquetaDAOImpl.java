package com.ashdz.webrevistas.DAO.Etiqueta;

import com.ashdz.webrevistas.model.Conexion;
import com.ashdz.webrevistas.model.Etiqueta;
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
public class EtiquetaDAOImpl implements EtiquetaDAO{
    private static EtiquetaDAOImpl etiquetaDAO = null;
    private Connection conexion = Conexion.getConexion();

    private EtiquetaDAOImpl(){}
    
    public static EtiquetaDAOImpl getEtiquetaDAO(){
        if (etiquetaDAO == null) {
            etiquetaDAO = new EtiquetaDAOImpl();
        }
        return etiquetaDAO;
    }

    @Override
    public ResultSet getResultSetEtiqueta() {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT * FROM Etiqueta";
            Statement declaracion = conexion.createStatement();
            
            rs = declaracion.executeQuery(sql);
            System.out.println("resutset de etiqueta obtenido");
            declaracion.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void createEtiqueta(String e) {
        try {
            String sql = "INSERT INTO Etiqueta (NombreEtiqueta) VALUES (?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, e);
            ps.executeUpdate();
            System.out.println("Etiqueta ingresada Correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se inserto la etiqueta");
            ex.printStackTrace();
        }
    }

    @Override
    public List<Etiqueta> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Etiqueta t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Etiqueta getObject(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Etiqueta e) {
        try {
            String sql = "UPDATE Etiqueta SET NombreEtiqueta = ? WHERE IdEtiqueta = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getId());
            ps.executeUpdate();
            System.out.println("Etiqueta actualizada");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se actualizo la etiqueta");
            ex.printStackTrace();
        } 
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
