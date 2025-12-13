/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_datos;

/**
 *
 * @author aldoi
 */
public class Mantenimiento {
    int id;
    Maquina maquina;
    String tipo;
    String fechaProgramada;
    String estado;
    String descripcion;

    public Mantenimiento(int id, Maquina maquina, String tipo, String fechaProgramada, String estado,
            String descripcion) {
        this.id = id;
        this.maquina = maquina;
        this.tipo = tipo;
        this.fechaProgramada = fechaProgramada;
        this.estado = estado;
        this.descripcion = descripcion;
    }
}
