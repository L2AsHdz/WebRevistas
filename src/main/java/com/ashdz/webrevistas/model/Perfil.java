package com.ashdz.webrevistas.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author asael
 */
public class Perfil {
    private int id;
    private InputStream foto;
    private InputStream fotoPredefinida;
    private String hobbies;
    private String temasInteres;
    private String descripcion;
    private LocalDate fechaNacimiento;
    private String sexo;

    public Perfil(HttpServletRequest request) throws FileNotFoundException {
        fotoPredefinida = new FileInputStream("/imagenes/predefinida.png");
        foto = fotoPredefinida;
        hobbies = request.getParameter("hobbies");
        temasInteres = request.getParameter("temasInteres");
        descripcion = request.getParameter("descripcion");
        fechaNacimiento = LocalDate.parse(request.getParameter("fechaN"));
        sexo = request.getParameter("sexo");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public InputStream getFotoPredefinida() {
        return fotoPredefinida;
    }

    public void setFotoPredefinida(InputStream fotoPredefinida) {
        this.fotoPredefinida = fotoPredefinida;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getTemasInteres() {
        return temasInteres;
    }

    public void setTemasInteres(String temasInteres) {
        this.temasInteres = temasInteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
