package com.ashdz.webrevistas.model;

import java.time.LocalDate;

/**
 *
 * @author asael
 */
public class Suscripcion {
    private int idRevista;
    private String emailSuscriptor;
    private LocalDate fechaSuscripcion;
    private int estadoSuscripcion;

    public Suscripcion() {
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

    public LocalDate getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public int getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(int estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }
}
