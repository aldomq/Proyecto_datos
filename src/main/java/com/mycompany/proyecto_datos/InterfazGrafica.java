/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_datos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aldoi
 */



public class InterfazGrafica extends JFrame {
    private GestorMantenimientos gestor;

    private JTable tablaMaquinas;
    private DefaultTableModel modeloMaquinas;

    private JTable tablaMantenimientos;
    private DefaultTableModel modeloMantenimientos;

    private SimpleDateFormat sdf;

    public InterfazGrafica(GestorMantenimientos gestor) {
        this.gestor = gestor;
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");

        setTitle("Sistema de Mantenimientos");
        setSize(980, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Maquinas", construirPanelMaquinas());
        tabs.add("Mantenimientos", construirPanelMantenimientos());

        add(tabs, BorderLayout.CENTER);

        cargarTablaMaquinas();
        cargarTablaMantenimientos(gestor.listarPendientes());
    }

    private JPanel construirPanelMaquinas() {
        JPanel panel = new JPanel(new BorderLayout());

        modeloMaquinas = new DefaultTableModel(new Object[] { "Id", "Nombre", "Tipo", "Ubicacion" }, 0);
        tablaMaquinas = new JTable(modeloMaquinas);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVer = new JButton("Ver maquinas");
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        acciones.add(btnVer);
        acciones.add(btnAgregar);
        acciones.add(btnEliminar);

        panel.add(acciones, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaMaquinas), BorderLayout.CENTER);

        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cargarTablaMaquinas();
            }
        });

        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                agregarMaquinaDialogo();
            }
        });

        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                eliminarMaquinaSeleccionada();
            }
        });

        return panel;
    }

    private JPanel construirPanelMantenimientos() {
        JPanel panel = new JPanel(new BorderLayout());

        modeloMantenimientos = new DefaultTableModel(
                new Object[] { "Id", "Maquina", "Tipo", "Fecha", "Estado", "Descripcion" }, 0);
        tablaMantenimientos = new JTable(modeloMantenimientos);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVerPendientes = new JButton("Ver pendientes");
        JButton btnVerTodo = new JButton("Ver todo");
        JButton btnEncolar = new JButton("Agregar a cola");
        JButton btnAtender = new JButton("Atender siguiente");
        JButton btnBuscarMaquina = new JButton("Buscar maquina");
        JButton btnBuscarTipo = new JButton("Buscar tipo");

        acciones.add(btnVerPendientes);
        acciones.add(btnVerTodo);
        acciones.add(btnEncolar);
        acciones.add(btnAtender);
        acciones.add(btnBuscarMaquina);
        acciones.add(btnBuscarTipo);

        panel.add(acciones, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaMantenimientos), BorderLayout.CENTER);

        btnVerPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cargarTablaMantenimientos(gestor.listarPendientes());
            }
        });

        btnVerTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cargarTablaMantenimientos(gestor.listarTodo());
            }
        });

        btnEncolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                encolarMantenimientoDialogo();
            }
        });

        btnAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                atenderSiguiente();
            }
        });

        btnBuscarMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(InterfazGrafica.this, "Nombre de maquina:");
                if (nombre != null) cargarTablaMantenimientos(gestor.buscarPorMaquina(nombre));
            }
        });

        btnBuscarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String tipo = JOptionPane.showInputDialog(InterfazGrafica.this, "Tipo (diario/semanal/mensual):");
                if (tipo != null) cargarTablaMantenimientos(gestor.buscarPorTipo(tipo));
            }
        });

        return panel;
    }

    private void agregarMaquinaDialogo() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        if (nombre == null || nombre.trim().length() == 0) return;

        String tipo = JOptionPane.showInputDialog(this, "Tipo:");
        if (tipo == null || tipo.trim().length() == 0) return;

        String ubicacion = JOptionPane.showInputDialog(this, "Ubicacion:");
        if (ubicacion == null) ubicacion = "";

        gestor.agregarMaquina(nombre.trim(), tipo.trim(), ubicacion.trim());
        cargarTablaMaquinas();
    }

    private void eliminarMaquinaSeleccionada() {
        int fila = tablaMaquinas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
            return;
        }

        int id = Integer.parseInt(modeloMaquinas.getValueAt(fila, 0).toString());
        int r = JOptionPane.showConfirmDialog(this, "Eliminar maquina id " + id + " ?", "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            boolean ok = gestor.eliminarMaquinaPorId(id);
            if (!ok) JOptionPane.showMessageDialog(this, "No se pudo eliminar");
            cargarTablaMaquinas();
        }
    }

    private void encolarMantenimientoDialogo() {
        List<Maquina> maquinas = gestor.listarMaquinas();
        if (maquinas.size() == 0) {
            JOptionPane.showMessageDialog(this, "No hay maquinas");
            return;
        }

        Object[] ops = maquinas.toArray();
        Object sel = JOptionPane.showInputDialog(this, "Seleccione maquina:", "Cola",
                JOptionPane.QUESTION_MESSAGE, null, ops, ops[0]);

        if (sel == null) return;
        Maquina maq = (Maquina) sel;

        String tipo = JOptionPane.showInputDialog(this, "Tipo (diario/semanal/mensual):");
        if (tipo == null || tipo.trim().length() == 0) return;

        String fechaStr = JOptionPane.showInputDialog(this, "Fecha (dd/MM/yyyy):");
        if (fechaStr == null || fechaStr.trim().length() == 0) return;

        Date fecha;
        try {
            fecha = sdf.parse(fechaStr.trim());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fecha invalida");
            return;
        }

        String descripcion = JOptionPane.showInputDialog(this, "Descripcion:");
        if (descripcion == null) descripcion = "";

        gestor.encolarMantenimiento(maq, tipo.trim().toLowerCase(), fecha, descripcion);
        cargarTablaMantenimientos(gestor.listarPendientes());
    }

    private void atenderSiguiente() {
        Mantenimiento siguiente = gestor.verSiguientePendiente();
        if (siguiente == null) {
            JOptionPane.showMessageDialog(this, "No hay pendientes");
            return;
        }

        String maq = "";
        if (siguiente.getMaquina() != null) maq = siguiente.getMaquina().getNombre();

        String fecha = "";
        if (siguiente.getFechaProgramada() != null) fecha = sdf.format(siguiente.getFechaProgramada());

        int r = JOptionPane.showConfirmDialog(this,
                "Atender siguiente?\nId: " + siguiente.getId() + "\nMaquina: " + maq + "\nFecha: " + fecha,
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            gestor.atenderSiguiente();
            cargarTablaMantenimientos(gestor.listarPendientes());
        }
    }

    private void cargarTablaMaquinas() {
        modeloMaquinas.setRowCount(0);
        List<Maquina> lista = gestor.listarMaquinas();
        for (int i = 0; i < lista.size(); i++) {
            Maquina m = lista.get(i);
            modeloMaquinas.addRow(new Object[] {
                    String.valueOf(m.getId()),
                    m.getNombre(),
                    m.getTipo(),
                    m.getUbicacion()
            });
        }
    }

    private void cargarTablaMantenimientos(List<Mantenimiento> lista) {
        modeloMantenimientos.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            Mantenimiento m = lista.get(i);
            String maq = m.getMaquina() != null ? m.getMaquina().getNombre() : "";
            String fecha = m.getFechaProgramada() != null ? sdf.format(m.getFechaProgramada()) : "";
            modeloMantenimientos.addRow(new Object[] {
                    String.valueOf(m.getId()),
                    maq,
                    m.getTipo(),
                    fecha,
                    m.getEstado(),
                    m.getDescripcion()
            });
        }
    }
}
