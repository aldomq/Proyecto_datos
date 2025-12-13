package com.mycompany.proyecto_datos;
public class NodoMantenimiento {
    Mantenimiento mantenimiento;
    NodoMantenimiento siguiente;
    NodoMantenimiento anterior;

    public NodoMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimiento = mantenimiento;
    }
}

