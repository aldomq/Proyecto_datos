import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * Proyecto: interfaz_grafica
 * IDE: NetBeans
 * Archivo main: interfaz.java
 * Tipo: Interfaz gráfica con Java Swing
 *
 * Esta clase representa la ventana principal del sistema de administración
 * (productos, clientes, proveedores y ventas) para subir a GitHub.
 */
public class interfaz extends JFrame {

    // Componentes principales
    private JTabbedPane pestañas;

    // === Productos ===
    private JTable tablaProductos;
    private DefaultTableModel modeloProductos;
    private JTextField txtNombreProducto, txtStockProducto, txtPrecioProducto;

    // === Clientes ===
    private JTable tablaClientes;
    private DefaultTableModel modeloClientes;

    // === Proveedores ===
    private JTable tablaProveedores;
    private DefaultTableModel modeloProveedores;

    public interfaz() {
        setTitle("Llanos del Sol - Sistema de Gestión");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponentes();
    }

    private void initComponentes() {
        pestañas = new JTabbedPane();

        pestañas.addTab("Productos", panelProductos());
        pestañas.addTab("Clientes", panelClientes());
        pestañas.addTab("Proveedores", panelProveedores());
        pestañas.addTab("Ventas", panelVentas());

        add(pestañas, BorderLayout.CENTER);
    }

    // ================= PANEL PRODUCTOS =================
    private JPanel panelProductos() {
        JPanel panel = new JPanel(new BorderLayout());

        // Tabla
        modeloProductos = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Stock", "Precio"}, 0
        );
        tablaProductos = new JTable(modeloProductos);
        JScrollPane scroll = new JScrollPane(tablaProductos);

        // Formulario
        JPanel formulario = new JPanel(new GridLayout(2, 4, 5, 5));
        txtNombreProducto = new JTextField();
        txtStockProducto = new JTextField();
        txtPrecioProducto = new JTextField();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        formulario.add(new JLabel("Nombre:"));
        formulario.add(new JLabel("Stock:"));
        formulario.add(new JLabel("Precio:"));
        formulario.add(new JLabel("Acciones"));

        formulario.add(txtNombreProducto);
        formulario.add(txtStockProducto);
        formulario.add(txtPrecioProducto);
        formulario.add(btnAgregar);

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnEliminar, BorderLayout.SOUTH);

        // Eventos
        btnAgregar.addActionListener(e -> agregarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());

        return panel;
    }

    private void agregarProducto() {
        if (txtNombreProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del producto");
            return;
        }

        modeloProductos.addRow(new Object[]{
                modeloProductos.getRowCount() + 1,
                txtNombreProducto.getText(),
                txtStockProducto.getText(),
                txtPrecioProducto.getText()
        });

        txtNombreProducto.setText("");
        txtStockProducto.setText("");
        txtPrecioProducto.setText("");
    }

    private void eliminarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila >= 0) {
            modeloProductos.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
        }
    }

    // ================= PANEL CLIENTES =================
    private JPanel panelClientes() {
        JPanel panel = new JPanel(new BorderLayout());

        modeloClientes = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Correo"}, 0
        );
        tablaClientes = new JTable(modeloClientes);

        JButton btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.addActionListener(e ->
                modeloClientes.addRow(new Object[]{
                        modeloClientes.getRowCount() + 1,
                        "Nuevo Cliente",
                        "correo@email.com"
                })
        );

        panel.add(new JScrollPane(tablaClientes), BorderLayout.CENTER);
        panel.add(btnAgregar, BorderLayout.SOUTH);

        return panel;
    }

    // ================= PANEL PROVEEDORES =================
    private JPanel panelProveedores() {
        JPanel panel = new JPanel(new BorderLayout());

        modeloProveedores = new DefaultTableModel(
                new Object[]{"ID", "Nombre"}, 0
        );
        tablaProveedores = new JTable(modeloProveedores);

        JButton btnAgregar = new JButton("Agregar Proveedor");
        btnAgregar.addActionListener(e ->
                modeloProveedores.addRow(new Object[]{
                        modeloProveedores.getRowCount() + 1,
                        "Nuevo Proveedor"
                })
        );

        panel.add(new JScrollPane(tablaProveedores), BorderLayout.CENTER);
        panel.add(btnAgregar, BorderLayout.SOUTH);

        return panel;
    }

    // ================= PANEL VENTAS =================
    private JPanel panelVentas() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Módulo de ventas en construcción"));
        return panel;
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new interfaz().setVisible(true);
        });
    }
}

/*
NOTAS PARA GITHUB / NETBEANS:
- Crear proyecto Java Application llamado: interfaz_grafica
- Colocar este archivo como interfaz.java
- No requiere librerías externas (solo Swing)
- Compatible con NetBeans 8+ y JDK 8+
- Puede ampliarse conectando base de datos (MySQL, SQLite, etc.)
*/
