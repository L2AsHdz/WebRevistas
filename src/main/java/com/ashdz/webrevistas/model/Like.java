package com.ashdz.webrevistas.model;

import java.time.LocalDate;

/**
 *
 * @author asael
 */
public class Like {
    private int idRevista;
    private String emailSuscriptor;
    private LocalDate fechaLike;

    public Like() {
    }

    public Like(int idRevista, String emailSuscriptor, LocalDate fechaLike) {
        this.idRevista = idRevista;
        this.emailSuscriptor = emailSuscriptor;
        this.fechaLike = fechaLike;
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

    public LocalDate getFechaLike() {
        return fechaLike;
    }

    public void setFechaLike(LocalDate fechaLike) {
        this.fechaLike = fechaLike;
    }
}
