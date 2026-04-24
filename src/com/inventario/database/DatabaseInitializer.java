package com.inventario.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void inicializar() {

        try (Connection connGlobal = Conexion.conectarGlobal();
             Connection connTienda = Conexion.conectarTienda();
             Statement stmtGlobal = connGlobal.createStatement();
             Statement stmtTienda = connTienda.createStatement()) {

            // =========================
            // TABLAS GLOBAL
            // =========================

            stmtGlobal.execute("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    usuario TEXT UNIQUE NOT NULL,
                    contraseña TEXT NOT NULL,
                    rol TEXT NOT NULL,
                    activo INTEGER DEFAULT 1
                );
            """);

            stmtGlobal.execute("""
                CREATE TABLE IF NOT EXISTS permisos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_usuario INTEGER,
                    inventario INTEGER DEFAULT 1,
                    ventas INTEGER DEFAULT 1,
                    reportes INTEGER DEFAULT 1,
                    gastos INTEGER DEFAULT 1,
                    estadisticas INTEGER DEFAULT 1,
                    empleados INTEGER DEFAULT 0,
                    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
                );
            """);

            // =========================
            // TABLAS TIENDA
            // =========================

            stmtTienda.execute("""
                CREATE TABLE IF NOT EXISTS categorias (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL
                );
            """);

            stmtTienda.execute("""
                CREATE TABLE IF NOT EXISTS productos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    precio REAL NOT NULL,
                    costo REAL,
                    stock REAL DEFAULT 0,
                    imagen TEXT,
                    id_categoria INTEGER,
                    activo INTEGER DEFAULT 1,
                    FOREIGN KEY (id_categoria) REFERENCES categorias(id)
                );
            """);

            stmtTienda.execute("""
                CREATE TABLE IF NOT EXISTS ventas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    fecha TEXT NOT NULL,
                    total REAL NOT NULL,
                    metodo_pago TEXT,
                    efectivo_recibido REAL,
                    vuelto REAL,
                    tipo TEXT,
                    id_usuario INTEGER
                );
            """);

            stmtTienda.execute("""
                CREATE TABLE IF NOT EXISTS detalle_venta (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_venta INTEGER,
                    id_producto INTEGER,
                    cantidad REAL,
                    precio REAL,
                    subtotal REAL,
                    FOREIGN KEY (id_venta) REFERENCES ventas(id),
                    FOREIGN KEY (id_producto) REFERENCES productos(id)
                );
            """);

            stmtTienda.execute("""
                CREATE TABLE IF NOT EXISTS gastos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    descripcion TEXT,
                    monto REAL,
                    fecha TEXT,
                    id_usuario INTEGER
                );
            """);

            stmtTienda.execute("""
                CREATE TABLE IF NOT EXISTS movimientos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    tipo TEXT,
                    descripcion TEXT,
                    monto REAL,
                    fecha TEXT
                );
            """);

            // =========================
            // INSERTAR CATEGORÍAS SI NO EXISTEN
            // =========================

            stmtTienda.execute("""
                INSERT INTO categorias (nombre)
                SELECT 'Productos'
                WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE nombre='Productos');
            """);

            stmtTienda.execute("""
                INSERT INTO categorias (nombre)
                SELECT 'Cases'
                WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE nombre='Cases');
            """);

            stmtTienda.execute("""
                INSERT INTO categorias (nombre)
                SELECT 'Servicios'
                WHERE NOT EXISTS (SELECT 1 FROM categorias WHERE nombre='Servicios');
            """);

            System.out.println("Base de datos inicializada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}