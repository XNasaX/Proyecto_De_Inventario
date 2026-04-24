package com.inventario.vista;

import com.inventario.dao.ProductoDAO;
import com.inventario.modelo.Producto;

import javax.swing.*;
import java.awt.*;

public class EditarProductoDialog extends JDialog {

    private JTextField txtNombre, txtPrecio, txtCosto, txtStock;
    private Producto producto;

    public EditarProductoDialog(JFrame parent, Producto producto) {
        super(parent, "Editar Producto", true);

        this.producto = producto;

        setSize(300, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 5, 5));

        txtNombre = new JTextField(producto.getNombre());
        txtPrecio = new JTextField(String.valueOf(producto.getPrecio()));
        txtCosto = new JTextField(String.valueOf(producto.getCosto()));
        txtStock = new JTextField(String.valueOf(producto.getStock()));

        JButton btnGuardar = new JButton("Actualizar");

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

        btnGuardar.addActionListener(e -> actualizar());
    }

    private void actualizar() {
        try {
            producto = new Producto(
                producto.getId(),
                txtNombre.getText(),
                Double.parseDouble(txtPrecio.getText()),
                Double.parseDouble(txtCosto.getText()),
                Double.parseDouble(txtStock.getText()),
                producto.getIdCategoria()
            );

            ProductoDAO.actualizar(producto);

            JOptionPane.showMessageDialog(this, "Producto actualizado");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar");
        }
    }
}