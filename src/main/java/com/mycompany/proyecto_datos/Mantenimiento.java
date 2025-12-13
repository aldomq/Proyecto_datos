/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_datos;

/**
 *
 * @author aldoi
 */
import java.util.Date;

public class Mantenimiento {
    private int id;
    private Maquina maquina;
    private String tipo;
    private Date fechaProgramada;
    private String estado;
    private String descripcion;

    public Mantenimiento(int id, Maquina maquina, String tipo, Date fechaProgramada, String estado, String descripcion) {
        this.id = id;
        this.maquina = maquina;
        this.tipo = tipo;
        this.fechaProgramada = fechaProgramada;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
