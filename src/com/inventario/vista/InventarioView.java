package com.inventario.vista;

import com.inventario.dao.ProductoDAO;
import com.inventario.modelo.Producto;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class InventarioView extends JFrame {

    private JPanel panelContenido;
    private JPanel panelProductos;
    private JTextField buscador;

    public InventarioView() {
        setTitle("Inventario");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // ======================
        // SIDEBAR
        // ======================
        
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(58, 134, 255));
        sidebar.setPreferredSize(new Dimension(200, 600));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        String[] opciones = {"Balance", "Inventario", "Ventas", "Movimientos", "Reportes"};

        for (String opcion : opciones) {
            JButton btn = new JButton(opcion);
            btn.setFocusPainted(false);
            btn.setMaximumSize(new Dimension(180, 40));
            sidebar.add(Box.createVerticalStrut(10));
            sidebar.add(btn);
        }

        // ======================
        // PANEL PRINCIPAL
        // ======================
        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(new Color(245, 247, 250));

        // ======================
        // HEADER
        // ======================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buscador = new JTextField();
        buscador.setPreferredSize(new Dimension(200, 30));
        buscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

        public void insertUpdate(javax.swing.event.DocumentEvent e) {
        buscarProductos(buscador.getText());
        }

        public void removeUpdate(javax.swing.event.DocumentEvent e) {
        buscarProductos(buscador.getText());
        }

        public void changedUpdate(javax.swing.event.DocumentEvent e) {
        buscarProductos(buscador.getText());
        }
    });
        

        JButton btnAgregar = new JButton("Agregar producto");
        btnAgregar.setBackground(new Color(58, 134, 255));
        btnAgregar.setForeground(Color.WHITE);
        
        btnAgregar.addActionListener(e -> {
        AgregarProductoDialog dialog = new AgregarProductoDialog(this);
        dialog.setVisible(true);

        cargarProductosDesdeBD(); // 🔥 refresca después de guardar
        });

        header.add(buscador, BorderLayout.CENTER);
        header.add(btnAgregar, BorderLayout.EAST);

        // ======================
        // PANEL PRODUCTOS (GRID)
        // ======================
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 4, 10, 10));
        panelProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelProductos.setBackground(new Color(245, 247, 250));

        // Scroll
        JScrollPane scroll = new JScrollPane(panelProductos);
        scroll.setBorder(null);

        panelContenido.add(header, BorderLayout.NORTH);
        panelContenido.add(scroll, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        // Datos de prueba
        cargarProductosDesdeBD();
    }

    // ======================
    // TARJETA DE PRODUCTO
    // ======================
    
        private JPanel crearTarjeta(Producto p) {
            
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(229,231,235)));

        JLabel img = new JLabel("IMG");
        img.setPreferredSize(new Dimension(100, 80));
        img.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNombre = new JLabel(p.getNombre());
        JLabel lblPrecio = new JLabel("S/ " + p.getPrecio());
        JLabel lblStock = new JLabel(p.getStock() + " disponibles");

        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblStock.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalStrut(10));
        card.add(img);
        card.add(Box.createVerticalStrut(10));
        card.add(lblNombre);
        card.add(lblPrecio);
        card.add(lblStock);
        card.add(Box.createVerticalStrut(10));

        // 🔥 CLICK PARA EDITAR
        
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditarProductoDialog dialog = new EditarProductoDialog(null, p);
                dialog.setVisible(true);

            cargarProductosDesdeBD(); // refrescar
        }
    });

    return card;
}
    
        private void cargarProductosDesdeBD() {

            panelProductos.removeAll();

            List<Producto> lista = ProductoDAO.listar();

            for (Producto p : lista) {
                panelProductos.add(
                    crearTarjeta(p)
                );
            }

            panelProductos.revalidate();
            panelProductos.repaint();
    }

        private void buscarProductos(String texto) {

            if (texto.trim().isEmpty()) {
                cargarProductosDesdeBD();
                return;
            }

            panelProductos.removeAll();

            List<Producto> lista = ProductoDAO.buscar(texto);

            for (Producto p : lista) {
                panelProductos.add(
                    crearTarjeta(p)
                );
            }

            panelProductos.revalidate();
            panelProductos.repaint();
        }
    
    private void limpiarProductos() {
    panelProductos.removeAll();
    panelProductos.revalidate();
    panelProductos.repaint();
    }
}