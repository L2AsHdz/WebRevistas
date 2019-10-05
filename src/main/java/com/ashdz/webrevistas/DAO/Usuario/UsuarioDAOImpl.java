package com.ashdz.webrevistas.DAO.Usuario;

import com.ashdz.webrevistas.model.Conexion;
import com.ashdz.webrevistas.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asael
 */
public class UsuarioDAOImpl implements UsuarioDAO{
    private static UsuarioDAOImpl userDAO = null;
    private Connection conexion = Conexion.getConexion();

    private UsuarioDAOImpl(){}
    
    public static UsuarioDAOImpl getUserDAO(){
        if (userDAO == null) {
            userDAO = new UsuarioDAOImpl();
        }
        return userDAO;
    }
    
    @Override
    public List<Usuario> getListado() {
        List<Usuario> usuarios = null;
        
        try {
            String sql = "SELECT * FROM Usuario";
            Statement declaracion = conexion.createStatement();
            
            usuarios = new ArrayList();
            ResultSet rs = declaracion.executeQuery(sql);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setEmailUsuario(rs.getString("EmailUsuario"));
                usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                usuario.setUsuarioSistema(rs.getString("UsuarioSistema"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setTipoUsuario(rs.getInt("TipoUsuario"));
                usuarios.add(usuario);
            }
            System.out.println("Listado de Usuarios Obtenido");
            rs.close();
            declaracion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void create(Usuario u) {
        try {
            String sql = "INSERT INTO Usuario (EmailUsuario, NombreUsuario, UsuarioSistema, Password,"
                    + " TipoUsuario) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, u.getEmailUsuario());
            ps.setString(2, u.getNombreUsuario());
            ps.setString(3, u.getUsuarioSistema());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getTipoUsuario());
            ps.executeUpdate();
            System.out.println("Usuario Ingresado Correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se inserto el usuario");
            ex.printStackTrace();
        }
    }

    @Override
    public Usuario getObject(Object e) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public void update(Usuario u) {
        try {
            String sql = "UPDATE Usuario SET NombreUsuario = ?, UsuarioSistema = ?,"
            + "Password = ? WHERE EmailUsuario = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getUsuarioSistema());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getEmailUsuario());
            ps.executeUpdate();
            System.out.println("Usuario actualizado");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se actualizo el registro");
            ex.printStackTrace();
        } 
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Usuario getByEmail(Object email) {
        Usuario u = new Usuario();
        try {
            String sql = "SELECT * FROM Usuario WHERE EmailUsuario = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, (String)email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                u.setEmailUsuario(rs.getString("EmailUsuario"));
                u.setNombreUsuario(rs.getString("NombreUsuario"));
                u.setUsuarioSistema(rs.getString("UsuarioSistema"));
                u.setPassword(rs.getString("Password"));
                u.setTipoUsuario(rs.getInt("TipoUsuario"));
            }else{
                u = null;
            }
            
            System.out.println("Usuario obtenido de la BD");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo leer el usuario");
            ex.printStackTrace();
        }
        return u;
    }

    @Override
    public Usuario getByUserName(Object userName) {
        Usuario u = new Usuario();
        try {
            String sql = "SELECT * FROM Usuario WHERE UsuarioSistema = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, (String)userName);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                u.setEmailUsuario(rs.getString("EmailUsuario"));
                u.setNombreUsuario(rs.getString("NombreUsuario"));
                u.setUsuarioSistema(rs.getString("UsuarioSistema"));
                u.setPassword(rs.getString("Password"));
                u.setTipoUsuario(rs.getInt("TipoUsuario"));
            }else{
                u = null;
            }
            
            System.out.println("Usuario obtenido de la BD");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo leer el usuario");
            ex.printStackTrace();
        }
        return u;
    }
    
}
