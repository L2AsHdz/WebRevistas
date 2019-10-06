package com.ashdz.webrevistas.DAO.Categoria;

import com.ashdz.webrevistas.model.Categoria;
import com.ashdz.webrevistas.model.Conexion;
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
public class CategoriaDAOImpl implements CategoriaDAO{
    private static CategoriaDAOImpl categoDAO = null;
    private Connection conexion = Conexion.getConexion();

    private CategoriaDAOImpl(){}
    
    public static CategoriaDAOImpl getCategoriaDAO(){
        if (categoDAO == null) {
            categoDAO = new CategoriaDAOImpl();
        }
        return categoDAO;
    }
    
    @Override
    public List<Categoria> getListado() {
        List<Categoria> categorias = null;
        
        try {
            String sql = "SELECT * FROM Categoria";
            Statement declaracion = conexion.createStatement();
            
            categorias = new ArrayList();
            ResultSet rs = declaracion.executeQuery(sql);
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("IdCategoria"));
                c.setNombre(rs.getString("NombreCategoria"));
                categorias.add(c);
            }
            System.out.println("Listado de categorias Obtenido");
            rs.close();
            declaracion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return categorias;
    }

    @Override
    public void create(Categoria c) {
        try {
            String sql = "INSERT INTO Categoria (NombreCategoria) VALUES (?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.executeUpdate();
            System.out.println("Categoria ingresada Correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se inserto la categoria");
            ex.printStackTrace();
        }
    }

    @Override
    public Categoria getObject(Object id) {
        Categoria c = new Categoria();
        try {
            String sql = "SELECT * FROM Usuario WHERE EmailUsuario = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, (int)id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                c.setId(rs.getInt("IdCategoria"));
                c.setNombre(rs.getString("NombreCategoria"));
            }else{
                c = null;
            }
            
            System.out.println("Categooria obtenida de la BD");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo leer la categoria");
            ex.printStackTrace();
        }
        return c;
    }

    @Override
    public void update(Categoria c) {
        try {
            String sql = "UPDATE Categoria SET NombreCategoria = ? WHERE IdCategoria = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getId());
            ps.executeUpdate();
            System.out.println("Categoria actualizada");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se actualizo la categoria");
            ex.printStackTrace();
        } 
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getResultSet() {
        ResultSet rs = null;
        
        try {
            String sql = "SELECT * FROM Categoria";
            Statement declaracion = conexion.createStatement();
            
            rs = declaracion.executeQuery(sql);
            System.out.println("resutset de categoria obtenido");
            declaracion.close();
        } catch (SQLException e) {
            rs=null;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void createCat(String c) {
        try {
            String sql = "INSERT INTO Categoria (NombreCategoria) VALUES (?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, c);
            ps.executeUpdate();
            System.out.println("Categoria ingresada Correctamente");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se inserto la categoria");
            ex.printStackTrace();
        }
    }
    
}
