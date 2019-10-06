package com.ashdz.webrevistas.model;

import java.time.LocalDate;

/**
 *
 * @author asael
 */
public class Comentario {
    private int idComentario;
    private int idRevista;
    private String emailSuscriptor;
    private LocalDate fechaComentario;
    private String comentario;

    public Comentario() {
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getEmailSuscriptor() {
        return emailSuscriptor;
    }

    public void setEmailSuscriptor(String emailSuscriptor) {
        this.emailSuscriptor = emailSuscriptor;
    }

    public LocalDate getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDate fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
