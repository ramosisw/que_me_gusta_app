package com.wayproyect.que_me_gusta.entidades;

import com.wayproyect.que_me_gusta.enumeradores.Tipo;

/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 14/10/13
 * Time: 06:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueMeGusta {
    private int id;
    private Tipo tipo;
    private String nombre;
    private boolean meGusta;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isMeGusta() {
        return meGusta;
    }

    public void setMeGusta(boolean meGusta) {
        this.meGusta = meGusta;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
