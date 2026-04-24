package com.inventario.dao;

import com.inventario.database.Conexion;
import com.inventario.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // ======================
    // GUARDAR PRODUCTO
    // ======================
    public static void guardar(Producto p) {

        String sql = "INSERT INTO productos (nombre, precio, costo, stock, id_categoria) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.conectarTienda();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setDouble(3, p.getCosto());
            stmt.setDouble(4, p.getStock());
            stmt.setInt(5, p.getIdCategoria());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ======================
    // LISTAR PRODUCTOS
    // ======================
    public static List<Producto> listar() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos WHERE activo = 1";

        try (Connection conn = Conexion.conectarTienda();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDouble("costo"),
                        rs.getDouble("stock"),
                        rs.getInt("id_categoria")
                );
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public static List<Producto> buscar(String texto) {

    List<Producto> lista = new ArrayList<>();

    String sql = "SELECT * FROM productos WHERE activo = 1 AND nombre LIKE ?";

    try (Connection conn = Conexion.conectarTienda();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + texto + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Producto p = new Producto(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                rs.getDouble("costo"),
                rs.getDouble("stock"),
                rs.getInt("id_categoria")
            );
            lista.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}   
    public static void actualizar(Producto p) {

    String sql = "UPDATE productos SET nombre=?, precio=?, costo=?, stock=?, id_categoria=? WHERE id=?";

    try (Connection conn = Conexion.conectarTienda();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, p.getNombre());
        stmt.setDouble(2, p.getPrecio());
        stmt.setDouble(3, p.getCosto());
        stmt.setDouble(4, p.getStock());
        stmt.setInt(5, p.getIdCategoria());
        stmt.setInt(6, p.getId());

        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}