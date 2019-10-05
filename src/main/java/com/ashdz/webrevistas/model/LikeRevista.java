package com.ashdz.webrevistas.model;

import java.time.LocalDate;

/**
 *
 * @author asael
 */
public class LikeRevista {
    private int idRevista;
    private String emailSuscriptor;
    private LocalDate fechaLike;

    public LikeRevista() {
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
