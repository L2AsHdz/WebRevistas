package com.ashdz.webrevistas.model;

import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author asael
 */
public class Publicacion {
    private int id;
    private int idrevista;
    private LocalDate fechaPublicacion;
    private int noEdicion;
    private InputStream pdf;
    private byte[] archivoPDF;

    public Publicacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdrevista() {
        return idrevista;
    }

    public void setIdrevista(int idrevista) {
        this.idrevista = idrevista;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNoEdicion() {
        return noEdicion;
    }

    public void setNoEdicion(int noEdicion) {
        this.noEdicion = noEdicion;
    }

    public InputStream getPdf() {
        return pdf;
    }

    public void setPdf(InputStream pdf) {
        this.pdf = pdf;
    }

    public byte[] getArchivoPDF() {
        return archivoPDF;
    }

    public void setArchivoPDF(byte[] archivoPDF) {
        this.archivoPDF = archivoPDF;
    }
}
