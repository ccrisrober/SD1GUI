/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015.models;

import com.distribuidos.sd12015.Common;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class Duple {
    private Date e;
    private String f;
    
    private String converted;

    public Duple(Date e, String f) {
        this.e = e;
        this.f = f;
        try {
            this.converted = Common.dateToStr(e);
        } catch (ParseException ex) {
            Logger.getLogger(Duple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getE() {
        return e;
    }

    public void setE(Date e) {
        this.e = e;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.e);
        hash = 37 * hash + Objects.hashCode(this.f);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Duple other = (Duple) obj;
        if(other.converted.compareToIgnoreCase(this.converted) == 0) {
            if(other.f.compareToIgnoreCase(this.f) == 0) {
                return true;
            }
        }
        return false;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }
    
}
