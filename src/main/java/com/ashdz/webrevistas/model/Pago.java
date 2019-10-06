package com.ashdz.webrevistas.model;

import java.time.LocalDate;

/**
 *
 * @author asael
 */
public class Pago {
    private int id;
    private int idRevista;
    private String emailSuscriptor;
    private LocalDate fechaPago;
    private float pagoS;
    private float porcentajeA;
    private float costoR;

    public Pago() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public float getPagoS() {
        return pagoS;
    }

    public void setPagoS(float pagoS) {
        this.pagoS = pagoS;
    }

    public float getPorcentajeA() {
        return porcentajeA;
    }

    public void setPorcentajeA(float porcentajeA) {
        this.porcentajeA = porcentajeA;
    }

    public float getCostoR() {
        return costoR;
    }

    public void setCostoR(float costoR) {
        this.costoR = costoR;
    }
}
