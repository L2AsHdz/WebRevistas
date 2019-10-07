package com.ashdz.webrevistas.model;

import com.ashdz.webrevistas.DAO.Precios.PrecioDAO;
import com.ashdz.webrevistas.DAO.Precios.PrecioDAOImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author asael
 */
public class Revista {
    private int idRevista;
    private String emailEditor;
    private int idCategoria;
    private String nombreRevista;
    private float cuotaSuscripcion;
    private float costoPorDia;
    private int estadoComentarios;
    private int estadoLikes;
    private int estadoSuscripciones;
    private String descripcion;
    private float porcentajeAdmin;
    private Usuario user;
    private PrecioDAO precioDAO;

    public Revista() {
    }
    
    public Revista(HttpServletRequest request) {
        precioDAO = PrecioDAOImpl.getPrecioDAO();
        user = (Usuario)request.getSession().getAttribute("usuario");
        emailEditor = user.getEmailUsuario();
        idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        nombreRevista = request.getParameter("nombre");
        costoPorDia = precioDAO.getPrecio(1);
        descripcion = request.getParameter("descrip");
        porcentajeAdmin = precioDAO.getPrecio(2);
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getEmailEditor() {
        return emailEditor;
    }

    public void setEmailEditor(String emailEditor) {
        this.emailEditor = emailEditor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public float getCuotaSuscripcion() {
        return cuotaSuscripcion;
    }

    public void setCuotaSuscripcion(float cuotaSuscripcion) {
        this.cuotaSuscripcion = cuotaSuscripcion;
    }

    public float getCostoPorDia() {
        return costoPorDia;
    }

    public void setCostoPorDia(float costoPorDia) {
        this.costoPorDia = costoPorDia;
    }

    public int getEstadoComentarios() {
        return estadoComentarios;
    }

    public void setEstadoComentarios(int estadoComentarios) {
        this.estadoComentarios = estadoComentarios;
    }

    public int getEstadoLikes() {
        return estadoLikes;
    }

    public void setEstadoLikes(int estadoLikes) {
        this.estadoLikes = estadoLikes;
    }

    public int getEstadoSuscripciones() {
        return estadoSuscripciones;
    }

    public void setEstadoSuscripciones(int estadoSuscripciones) {
        this.estadoSuscripciones = estadoSuscripciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPorcentajeAdmin() {
        return porcentajeAdmin;
    }

    public void setPorcentajeAdmin(float porcentajeAdmin) {
        this.porcentajeAdmin = porcentajeAdmin;
    }
}
