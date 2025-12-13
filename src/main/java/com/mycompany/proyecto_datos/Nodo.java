/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_datos;

/**
 *
 * @author aldoi
 */
public class Nodo {

    private Maquina dato;
    private Nodo siguiente;

    public Nodo(Maquina dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Maquina getDato() {
        return dato;
    }

    public void setDato(Maquina dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
