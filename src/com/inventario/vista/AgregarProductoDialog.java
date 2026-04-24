package com.inventario.vista;

import com.inventario.dao.ProductoDAO;
import com.inventario.modelo.Producto;

import javax.swing.*;
import java.awt.*;

public class AgregarProductoDialog extends JDialog {

    private JTextField txtNombre, txtPrecio, txtCosto, txtStock;

    public AgregarProductoDialog(JFrame parent) {
        super(parent, "Agregar Producto", true);

        setSize(300, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 5, 5));

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtCosto = new JTextField();
        txtStock = new JTextField();

        JButton btnGuardar = new JButton("Guardar");

        add(new JLabel("Nombre:"));
        add(txtNombre);

        add(new JLabel("Precio:"));
        add(txtPrecio);

        add(new JLabel("Costo:"));
        add(txtCosto);

        add(new JLabel("Stock:"));
        add(txtStock);

        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarProducto());
    }

    private void guardarProducto() {
        try {
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            double costo = Double.parseDouble(txtCosto.getText());
            double stock = Double.parseDouble(txtStock.getText());

            int categoria = 1; // Productos por defecto

            Producto p = new Producto(nombre, precio, costo, stock, categoria);
            ProductoDAO.guardar(p);

            JOptionPane.showMessageDialog(this, "Producto guardado");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en datos");
        }
    }
}