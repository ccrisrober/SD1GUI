/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015.data;

/**
 *
 * @author USUARIO100
 */
public class ClaseConNif {
    private String Nif;

    public ClaseConNif(String Nif) {
        this.Nif = Nif;
    }

    public ClaseConNif(ClaseConNif nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNif() {
        return Nif;
    }

    public void setNif(String Nif) {
        this.Nif = Nif;
    }
    
    
}
