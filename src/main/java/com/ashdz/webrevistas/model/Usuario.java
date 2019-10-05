package com.ashdz.webrevistas.model;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author asael
 */
public class Usuario {
    private String emailUsuario;
    private String nombreUsuario;
    private String usuarioSistema;
    private String password;
    private int tipoUsuario;

    public Usuario() {
    }

    public Usuario(HttpServletRequest request) {
        emailUsuario = request.getParameter("email").toLowerCase();
        nombreUsuario = request.getParameter("nombre");
        usuarioSistema = request.getParameter("userS").toLowerCase();
        password = request.getParameter("pass");
        tipoUsuario = Integer.parseInt(request.getParameter("tipoUser"))+1;
        
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(String usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "emailUsuario=" + emailUsuario + ", nombreUsuario=" + nombreUsuario + ", usuarioSistema=" + usuarioSistema + ", password=" + password + ", tipoUsuario=" + tipoUsuario + '}';
    }
}
