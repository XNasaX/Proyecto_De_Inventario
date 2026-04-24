package com.inventario.modelo;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private double costo;
    private double stock;
    private int idCategoria;

    // 🔹 Constructor para NUEVOS productos (sin id)
    public Producto(String nombre, double precio, double costo, double stock, int idCategoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    // 🔹 Constructor para productos de la BD (con id)
    public Producto(int id, String nombre, double precio, double costo, double stock, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public double getCosto() { return costo; }
    public double getStock() { return stock; }
    public int getIdCategoria() { return idCategoria; }
}