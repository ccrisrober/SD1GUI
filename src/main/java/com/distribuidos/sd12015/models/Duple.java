/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015.models;

import java.util.Objects;

/**
 *
 * @author Cristian
 */
public class Duple<E, F> {
    public E e;
    public F f;

    public Duple(E e, F f) {
        this.e = e;
        this.f = f;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
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
        final Duple<?, ?> other = (Duple<?, ?>) obj;
        if (!Objects.equals(this.e, other.e)) {
            return false;
        }
        if (!Objects.equals(this.f, other.f)) {
            return false;
        }
        return true;
    }

    public F getF() {
        return f;
    }

    public void setF(F f) {
        this.f = f;
    }
    
}
