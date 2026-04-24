package com.inventario.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL_GLOBAL = "jdbc:sqlite:data/global.db";
    private static final String URL_TIENDA = "jdbc:sqlite:data/tienda1.db";

    public static Connection conectarGlobal() throws SQLException {
        return DriverManager.getConnection(URL_GLOBAL);
    }

    public static Connection conectarTienda() throws SQLException {
        return DriverManager.getConnection(URL_TIENDA);
    }
}