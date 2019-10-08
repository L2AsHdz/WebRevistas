package com.ashdz.webrevistas.DAO.EtiquetadoDAO;

import com.ashdz.webrevistas.model.Conexion;
import com.ashdz.webrevistas.model.Etiquetado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asael
 */
public class EtiquetadoDAOImpl implements EtiquetadoDAO{
    private static EtiquetadoDAOImpl etiqetaDAO = null;
    private Connection conexion = Conexion.getConexion();

    private EtiquetadoDAOImpl(){}
    
    public static EtiquetadoDAOImpl getEtiqueadoDAO(){
        if (etiqetaDAO == null) {
            etiqetaDAO = new EtiquetadoDAOImpl();
        }
        return etiqetaDAO;
    }
    
    @Override
    public List<Etiquetado> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Etiquetado t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Etiquetado getObject(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Etiquetado t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createEtiquetado(int idE, int idR) {
        try{
           String sql = "INSERT INTO EtiquetaRevista VALUES (?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idR);
            ps.setInt(2, idE);
            ps.executeUpdate();
            System.out.println("Revista etiquetada  correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se etiqueto la revista");
            ex.printStackTrace();
        }
    }
    
}
