package com.ashdz.webrevistas.DAO.Revista;

import com.ashdz.webrevistas.model.Conexion;
import com.ashdz.webrevistas.model.Revista;
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
public class RevistaDAOImpl implements RevistaDAO{
    private static RevistaDAOImpl revistaDAO = null;
    private Connection conexion = Conexion.getConexion();

    private RevistaDAOImpl(){}
    
    public static RevistaDAOImpl getRevistaDAO(){
        if (revistaDAO == null) {
            revistaDAO = new RevistaDAOImpl();
        }
        return revistaDAO;
    }
    
    @Override
    public List<Revista> getListado() {
        List<Revista> revistas = null;
        
        try {
            String sql = "SELECT * FROM Revista";
            Statement declaracion = conexion.createStatement();
            
            revistas = new ArrayList();
            ResultSet rs = declaracion.executeQuery(sql);
            while (rs.next()) {
                Revista revista = new Revista();
                revista.setIdRevista(rs.getInt("IdRevista"));
                revista.setEmailEditor(rs.getString("EmailEditor"));
                revista.setIdCategoria(rs.getInt("IdCategoria"));
                revista.setNombreRevista(rs.getString("NombreRevista"));
                revista.setCuotaSuscripcion(rs.getFloat("CuotaSuscripcion"));
                revista.setCostoPorDia(rs.getFloat("CostoPorDia"));
                revista.setEstadoComentarios(rs.getInt("EstadoComentarios"));
                revista.setEstadoLikes(rs.getInt("EstadoLikes"));
                revista.setEstadoSuscripciones(rs.getInt("EstadoSuscripciones"));
                revista.setDescripcion(rs.getString("Descripcion"));
                revista.setPorcentajeAdmin(rs.getFloat("PorcentajeAdmin"));
                revistas.add(revista);
            }
            System.out.println("Listado de Revistas Obtenido");
            rs.close();
            declaracion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return revistas;
    }

    @Override
    public void create(Revista r) {
        try {
            String sql = "INSERT INTO Revista (EmailEditor,IdCategoria,NombreRevista,CuotaSuscripcion,"
                    + "CostoPorDia,Descripcion,PorcentajeAdmin) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, r.getEmailEditor());
            ps.setInt(2, r.getIdCategoria());
            ps.setString(3, r.getNombreRevista());
            ps.setFloat(4, r.getCuotaSuscripcion());
            ps.setFloat(5, r.getCostoPorDia());
            ps.setString(6, r.getDescripcion());
            ps.setFloat(7, r.getPorcentajeAdmin());
            ps.executeUpdate();
            System.out.println("Revista ingresada correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se inserto la revista");
            ex.printStackTrace();
        }
    }

    @Override
    public Revista getObject(Object id) {
        Revista r = new Revista();
        try {
            String sql = "SELECT * FROM Revista WHERE IdRevista = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, (int)id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                r.setIdRevista(rs.getInt("IdRevista"));
                r.setEmailEditor(rs.getString("EmailEditor"));
                r.setIdCategoria(rs.getInt("IdCategoria"));
                r.setNombreRevista(rs.getString("NombreRevista"));
                r.setCuotaSuscripcion(rs.getFloat("CuotaSuscripcion"));
                r.setCostoPorDia(rs.getFloat("CostoPorDia"));
                r.setEstadoComentarios(rs.getInt("EstadoComentarios"));
                r.setEstadoLikes(rs.getInt("EstadoLikes"));
                r.setEstadoSuscripciones(rs.getInt("EstadoSuscripciones"));
                r.setDescripcion(rs.getString("Descripcion"));
                r.setPorcentajeAdmin(rs.getFloat("PorcentajeAdmin"));
            }else{
                r = null;
            }
            
            System.out.println("Revista obtenido de la BD");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo leer el usuario");
            ex.printStackTrace();
        }
        return r;
    }

    @Override
    public void update(Revista t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultSet getResultSetRev(String email) {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT r.IdRevista Id,r.Nombrerevista Nombre, u.NombreUsuario Editor, "
                    + "c.NombreCategoria Categoria, r.CuotaSuscripcion Precio FROM "
                    + "Revista r INNER JOIN Usuario u ON r.EmailEditor=u.EmailUsuario "
                    + "INNER JOIN Categoria c ON r.IdCategoria=c.IdCategoria WHERE EmailEditor=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            System.out.println("resutset de revista obtenido");
            ps.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ResultSet getResultSetRevAll(String email) {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT IdRevista, NombreRevista FROM Revista WHERE EmailEditor = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            System.out.println("resutset de revista obtenido");
            ps.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ResultSet getResultSetSearch(String s, String email) {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT r.IdRevista,r.Nombrerevista Nombre, u.NombreUsuario Editor, "
                    + "c.NombreCategoria Categoria, r.CuotaSuscripcion Precio FROM "
                    + "Revista r INNER JOIN Usuario u ON r.EmailEditor=u.EmailUsuario "
                    + "INNER JOIN Categoria c ON r.IdCategoria=c.IdCategoria WHERE "
                    + "r.EmailEditor='"+email+"' AND r.NombreRevista LIKE '%"+s+"%'";
            Statement declaracion = conexion.createStatement();
            
            rs = declaracion.executeQuery(sql);
            
            System.out.println("resutset de revista obtenido");
            declaracion.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ResultSet getResultSetRevByCat(String cat) {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT r.IdRevista,r.Nombrerevista Nombre, u.NombreUsuario Editor, "
                    + "c.NombreCategoria Categoria, r.CuotaSuscripcion, r.Descripcion Precio FROM "
                    + "Revista r INNER JOIN Usuario u ON r.EmailEditor=u.EmailUsuario "
                    + "INNER JOIN Categoria c ON r.IdCategoria=c.IdCategoria WHERE "
                    + "c.NombreCategoria LIKE '%"+cat+"%'";
            Statement declaracion = conexion.createStatement();
            
            rs = declaracion.executeQuery(sql);
            
            System.out.println("resutset de revista obtenido");
            declaracion.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ResultSet getResultSetSearchAll(String s) {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT r.IdRevista,r.Nombrerevista Nombre, u.NombreUsuario Editor, "
                    + "c.NombreCategoria Categoria, r.CuotaSuscripcion Precio FROM "
                    + "Revista r INNER JOIN Usuario u ON r.EmailEditor=u.EmailUsuario "
                    + "INNER JOIN Categoria c ON r.IdCategoria=c.IdCategoria WHERE "
                    + "r.NombreRevista LIKE '%"+s+"%'";
            Statement declaracion = conexion.createStatement();
            
            rs = declaracion.executeQuery(sql);
            
            System.out.println("resutset de revista obtenido");
            declaracion.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }
    
}
