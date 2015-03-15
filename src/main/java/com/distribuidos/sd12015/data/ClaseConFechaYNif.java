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
public class ClaseConFechaYNif {
    private String nif;
    private Date date;

    public ClaseConFechaYNif(Date date, String nif) {
        this.nif = nif;
        this.date = date;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
