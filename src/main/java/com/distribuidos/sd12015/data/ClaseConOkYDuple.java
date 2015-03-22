/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015.data;

import com.distribuidos.sd12015.models.Duple;
import java.util.Date;

/**
 *
 * @author Cristian
 */
public class ClaseConOkYDuple {
    private boolean ok;
    private Duple duple;

    public ClaseConOkYDuple(boolean ok, Duple duple) {
        this.ok = ok;
        this.duple = duple;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Duple getDuple() {
        return duple;
    }

    public void setDuple(Duple duple) {
        this.duple = duple;
    }
    
}
