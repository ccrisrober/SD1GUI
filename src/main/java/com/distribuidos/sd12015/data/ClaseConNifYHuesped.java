/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015.data;

import com.distribuidos.sd12015.models.Huesped;

/**
 *
 * @author USUARIO100
 */
public class ClaseConNifYHuesped {
    private String Nif;
    private Huesped huesped;

    public ClaseConNifYHuesped(String Nif, Huesped huesped) {
        this.Nif = Nif;
        this.huesped = huesped;
    }

    public String getNif() {
        return Nif;
    }

    public void setNif(String Nif) {
        this.Nif = Nif;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
    
    
}
