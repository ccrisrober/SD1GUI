/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015.data;

import java.util.Date;

/**
 *
 * @author USUARIO100
 */
public class ClaseConFechaEntradaYSalidaYNif {
    private String nif;
    private Date fechaEntrada;
    private Date fechaSalida;

    public ClaseConFechaEntradaYSalidaYNif(String nif, Date fechaEntrada, Date fechaSalida) {
        this.nif = nif;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        System.out.println("sgs");
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    
}
