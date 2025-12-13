/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_datos;

/**
 *
 * @author aldoi
 */
public class GestorMantenimientos {
    NodoMantenimiento inicio;
    NodoMantenimiento fin;

    public void agregarMantenimiento(Mantenimiento m) {
        NodoMantenimiento n = new NodoMantenimiento(m);
        if (inicio == null) {
            inicio = n;
            fin = n;
        } else {
            fin.siguiente = n;
            n.anterior = fin;
            fin = n;
        }
    }

    public void eliminarMantenimiento(int id) {
        NodoMantenimiento aux = inicio;
        while (aux != null && aux.mantenimiento.id != id) {
            aux = aux.siguiente;
        }
        if (aux == null)
            return;
        if (aux == inicio && aux == fin) {
            inicio = null;
            fin = null;
        } else if (aux == inicio) {
            inicio = inicio.siguiente;
            inicio.anterior = null;
        } else if (aux == fin) {
            fin = fin.anterior;
            fin.siguiente = null;
        } else {
            aux.anterior.siguiente = aux.siguiente;
            aux.siguiente.anterior = aux.anterior;
        }
    }

    public Mantenimiento buscarPorMaquina(String nombre) {
        NodoMantenimiento aux = inicio;
        while (aux != null) {
            if (aux.mantenimiento.maquina.nombre.equals(nombre)) {
                return aux.mantenimiento;
            }
            aux = aux.siguiente;
        }
        return null;
    }
}
