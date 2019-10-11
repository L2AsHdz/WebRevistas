package com.ashdz.webrevistas.DAO.Comentario;

import com.ashdz.webrevistas.model.Comentario;
import com.ashdz.webrevistas.model.Conexion;
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
public class ComentarioDAOImpl implements ComentarioDAO{
    private static ComentarioDAOImpl comentarioDAO = null;
    private Connection conexion = Conexion.getConexion();

    private ComentarioDAOImpl(){}
    
    public static ComentarioDAOImpl getComentarioDAO(){
        if (comentarioDAO == null) {
            comentarioDAO = new ComentarioDAOImpl();
        }
        return comentarioDAO;
    }
    
    
    @Override
    public ResultSet getResultSetComents() {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT c.IdComentario, u.UsuarioSistema, u.EmailUsuario, "
                    + "c.FechaComentario, c.Comentario FROM ComentarioRevista c "
                    + "INNER JOIN Usuario u ON c.EmailSuscriptor=u.EmailUsuario;";
            Statement declaracion = conexion.createStatement();
            
            rs = declaracion.executeQuery(sql);
            System.out.println("resutset de comentarios obtenido");
            declaracion.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public List<Comentario> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Comentario c) {
        try {
            String sql = "INSERT INTO ComentarioRevista(IdRevista,EmailSuscriptor,"
                    + "FechaComentario,Comentario) VALUES (?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, c.getIdRevista());
            ps.setString(2, c.getEmailSuscriptor());
            ps.setString(3, c.getFechaComentario().toString());
            ps.setString(4, c.getComentario());
            ps.executeUpdate();
            System.out.println("Comentario registrado Correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se registro el comentario");
            ex.printStackTrace();
        }
    }

    @Override
    public Comentario getObject(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Comentario t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
