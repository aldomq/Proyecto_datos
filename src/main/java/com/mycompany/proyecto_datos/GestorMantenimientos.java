/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_datos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aldoi
 */

public class GestorMantenimientos {

    private Nodo cabezaMaquinas;
    private int contadorMaquinas;

    private NodoMantenimiento frente;
    private NodoMantenimiento fin;

    private List<Mantenimiento> historial;
    private int contadorMantenimientos;

    public GestorMantenimientos() {
        cabezaMaquinas = null;
        contadorMaquinas = 0;

        frente = null;
        fin = null;

        historial = new ArrayList<Mantenimiento>();
        contadorMantenimientos = 0;
    }

    public Maquina agregarMaquina(String nombre, String tipo, String ubicacion) {
        contadorMaquinas++;
        Maquina m = new Maquina(contadorMaquinas, nombre, tipo, ubicacion);
        Nodo nuevo = new Nodo(m);

        if (cabezaMaquinas == null) {
            cabezaMaquinas = nuevo;
        } else {
            Nodo aux = cabezaMaquinas;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        return m;
    }

    public boolean eliminarMaquinaPorId(int id) {
        if (cabezaMaquinas == null) {
            return false;
        }

        if (cabezaMaquinas.getDato().getId() == id) {
            cabezaMaquinas = cabezaMaquinas.getSiguiente();
            return true;
        }

        Nodo ant = cabezaMaquinas;
        Nodo act = cabezaMaquinas.getSiguiente();

        while (act != null) {
            if (act.getDato().getId() == id) {
                ant.setSiguiente(act.getSiguiente());
                return true;
            }
            ant = act;
            act = act.getSiguiente();
        }
        return false;
    }

    public Maquina buscarMaquinaPorId(int id) {
        Nodo aux = cabezaMaquinas;
        while (aux != null) {
            if (aux.getDato().getId() == id) {
                return aux.getDato();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public List<Maquina> listarMaquinas() {
        List<Maquina> lista = new ArrayList<Maquina>();
        Nodo aux = cabezaMaquinas;
        while (aux != null) {
            lista.add(aux.getDato());
            aux = aux.getSiguiente();
        }
        return lista;
    }

    public boolean colaVacia() {
        return frente == null;
    }

    public Mantenimiento encolarMantenimiento(Maquina maquina, String tipo, Date fecha, String descripcion) {
        contadorMantenimientos++;
        Mantenimiento m = new Mantenimiento(
                contadorMantenimientos,
                maquina,
                tipo,
                fecha,
                "pendiente",
                descripcion
        );

        NodoMantenimiento nuevo = new NodoMantenimiento(m);

        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        return m;
    }

    public Mantenimiento verSiguientePendiente() {
        if (frente == null) {
            return null;
        }
        return frente.getDato();
    }

    public Mantenimiento atenderSiguiente() {
        if (frente == null) {
            return null;
        }

        Mantenimiento m = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }

        m.setEstado("completado");
        historial.add(m);
        return m;
    }

    public List<Mantenimiento> listarPendientes() {
        List<Mantenimiento> lista = new ArrayList<Mantenimiento>();
        NodoMantenimiento aux = frente;
        while (aux != null) {
            lista.add(aux.getDato());
            aux = aux.getSiguiente();
        }
        return lista;
    }

    public List<Mantenimiento> listarHistorial() {
        return historial;
    }

    public List<Mantenimiento> listarTodo() {
        List<Mantenimiento> todo = new ArrayList<Mantenimiento>();
        for (int i = 0; i < historial.size(); i++) {
            todo.add(historial.get(i));
        }
        NodoMantenimiento aux = frente;
        while (aux != null) {
            todo.add(aux.getDato());
            aux = aux.getSiguiente();
        }
        return todo;
    }

    public List<Mantenimiento> buscarPorMaquina(String texto) {
        List<Mantenimiento> res = new ArrayList<Mantenimiento>();
        if (texto == null) {
            return res;
        }

        String key = texto.trim().toLowerCase();
        List<Mantenimiento> todo = listarTodo();

        for (int i = 0; i < todo.size(); i++) {
            Mantenimiento m = todo.get(i);
            if (m.getMaquina() != null && m.getMaquina().getNombre() != null) {
                if (m.getMaquina().getNombre().toLowerCase().contains(key)) {
                    res.add(m);
                }
            }
        }
        return res;
    }

    public List<Mantenimiento> buscarPorTipo(String tipo) {
        List<Mantenimiento> res = new ArrayList<Mantenimiento>();
        if (tipo == null) {
            return res;
        }

        String key = tipo.trim().toLowerCase();
        List<Mantenimiento> todo = listarTodo();

        for (int i = 0; i < todo.size(); i++) {
            Mantenimiento m = todo.get(i);
            if (m.getTipo() != null && m.getTipo().toLowerCase().equals(key)) {
                res.add(m);
            }
        }
        return res;
    }
}
