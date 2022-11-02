package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Coneccao {

    public Connection conectar() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "123");
        props.setProperty("ssl", "yes");
        String URL_SERVIDOR = "jdbc:postgresql://localhost:5432/postgres";

        return DriverManager.getConnection(URL_SERVIDOR, props);
    }
     public void desconectar(Connection conn) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
}