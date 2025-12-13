/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_datos;
import javax.swing.SwingUtilities;
/**
 *
 * @author aldoi
 */


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GestorMantenimientos gestor = new GestorMantenimientos();
                InterfazGrafica ui = new InterfazGrafica(gestor);
                ui.setVisible(true);
            }
        });
    }
}
