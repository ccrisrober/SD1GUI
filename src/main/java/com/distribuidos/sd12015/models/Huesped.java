/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015.models;

import com.distribuidos.sd12015.Common;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Cristian
 */
public class Huesped {

    private String NIF;
    private String nombre;
    private String apellidos;
    private Date nacimiento;
    private Domicilio domicilio;

    // Opcional
    private String telefonoFijo;
    private String telefonoMovil;
    private String email;

    public Huesped() {
        this("", "", "");
    }

    public Huesped(String NIF, String nombre, String apellidos) {
        this(NIF, nombre, apellidos, null, new Domicilio());
        try {
            Date d = Common.strToDate("2015-21-03");
            this.setNacimiento(d);
        } catch (ParseException pe) {
        }
    }

    public Huesped(String NIF, String nombre, String apellidos, Date nacimiento) {
        this(NIF, nombre, apellidos, nacimiento, new Domicilio());
    }

    public Huesped(String NIF, String nombre, String apellidos, Date nacimiento, Domicilio domicilio) {
        this(NIF, nombre, apellidos, nacimiento, domicilio, "", "", "");
    }

    public Huesped(String NIF, String nombre, String apellidos, Date nacimiento, Domicilio domicilio, String telefonoFijo, String telefonoMovil, String email) {
        this.NIF = NIF;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacimiento = nacimiento;
        this.domicilio = domicilio;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.email = email;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Huesped{" + "NIF=" + NIF + ", nombre=" + nombre + ", apellidos=" + apellidos + ", nacimiento=" + nacimiento + ", domicilio=" + domicilio + ", telefonoFijo=" + telefonoFijo + ", telefonoMovil=" + telefonoMovil + ", email=" + email + '}';
    }

}
