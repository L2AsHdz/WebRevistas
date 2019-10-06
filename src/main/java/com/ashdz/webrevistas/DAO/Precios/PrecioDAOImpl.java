package com.ashdz.webrevistas.DAO.Precios;

import com.ashdz.webrevistas.model.Conexion;
import com.ashdz.webrevistas.model.PrecioGlobal;
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
public class PrecioDAOImpl implements PrecioDAO{
    private static PrecioDAOImpl precioDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private PrecioDAOImpl(){}
    
    public static PrecioDAOImpl getPrecioDAO(){
        if (precioDAO == null) {
            precioDAO = new PrecioDAOImpl();
        }
        return precioDAO;
    }

    @Override
    public List<PrecioGlobal> getListado() {
        List<PrecioGlobal> precios = null;
        
        try {
            String sql = "SELECT * FROM PreciosGlobales";
            Statement declaracion = conexion.createStatement();
            
            precios = new ArrayList();
            ResultSet rs = declaracion.executeQuery(sql);
            while (rs.next()) {
                PrecioGlobal precio = new PrecioGlobal();
                precio.setId(rs.getInt("IdCosto"));
                precio.setNombre(rs.getString("Nombre"));
                precio.setValor(rs.getFloat("Valor"));
                precios.add(precio);
            }
            System.out.println("Listado de Precios Obtenido");
            rs.close();
            declaracion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return precios;
    }

    @Override
    public void create(PrecioGlobal t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PrecioGlobal getObject(Object t) {
        PrecioGlobal precio = new PrecioGlobal();
        try {
            String sql = "SELECT * FROM PreciosGlobales WHERE IdCosto = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, (int)t);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                precio.setId(rs.getInt("IdCosto"));
                precio.setNombre(rs.getString("Nombre"));
                precio.setValor(rs.getFloat("Valor"));
            }else{
                precio = null;
            }
            
            System.out.println("Precio obtenido de la BD");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo leer el usuario");
            ex.printStackTrace();
        }
        return precio;
    }

    @Override
    public void update(PrecioGlobal u) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateValor(int id, float valor) {
        try {
            String sql = "UPDATE PreciosGlobales SET Valor = ? WHERE IdCosto = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setFloat(1, valor);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Precio actualizado");
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se actualizo el registro");
            ex.printStackTrace();
        } 
    }

    @Override
    public float getPrecio(int id) {
        float valor = 0;
        try {
            String sql = "SELECT Valor FROM PreciosGlobales WHERE IdCosto = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                valor = rs.getFloat("Valor");
            }
            System.out.println("Valor de Precio global obtenido");
            ps.close();
            ps = null;
        } catch (SQLException ex) {
            System.out.println("No se pudo leer el costo");
            ex.printStackTrace();
        }
        return valor;
    }
}
