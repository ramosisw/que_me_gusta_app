package com.wayproyect.que_me_gusta.enumeradores;

/**
 * Created with IntelliJ IDEA.
 * User: ADMIN
 * Date: 14/10/13
 * Time: 06:56 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Tipo {

    ADEREZOS("Aderezos", 0),
    CARNE_PROCESADA("Carne Procesada", 1),
    CARNES("Carnes", 2),
    ENSALADA("Ensalada", 3),
    FRUTAS("Frutas", 4),
    GRANOS("Granos", 5),
    LACTEOS("Lácteos", 6),
    MARISCOS("Mariscos", 7),
    PLATILLOS("Platillos", 8),
    POSTRES("Postres", 9),
    SALSAS("Salsas", 10),
    SOPAS("Sopas", 11),
    VERDURAS("Verduras", 12);

    private String nombre;
    private int valor;

    private Tipo(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
