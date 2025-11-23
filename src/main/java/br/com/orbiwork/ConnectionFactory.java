package br.com.orbiwork.dao;

import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
public class ConnectionFactory {

    private static final String URL = System.getProperty("quarkus.datasource.jdbc.url");
    private static final String USER = System.getProperty("quarkus.datasource.username");
    private static final String PASS = System.getProperty("quarkus.datasource.password");

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver PostgreSQL não encontrado.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao PostgreSQL.", e);
        }
    }
}
