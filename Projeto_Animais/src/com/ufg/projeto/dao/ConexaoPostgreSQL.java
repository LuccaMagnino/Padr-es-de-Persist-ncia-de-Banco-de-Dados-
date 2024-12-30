package com.ufg.projeto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    private static final String URL = "jdbc:postgresql://localhost:5432/animais_db";
    private static final String USUARIO = "postgres"; //Quando for criar o servidor, mude as informações                                              
    private static final String SENHA = "senha";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage(), e);
        }
    }
}
