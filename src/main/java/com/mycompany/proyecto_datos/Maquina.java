/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_datos;

/**
 *
 * @author aldoi
 */
public class Maquina {
    private int id;
    private String nombre;
    private String tipo;
    private String ubicacion;

    public Maquina(int id, String nombre, String tipo, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String toString() {
        return id + " - " + nombre;
    }
}
