package com.ufg.projeto.dao;

import com.ufg.projeto.modelos.Animal;
import com.ufg.projeto.modelos.Habitat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    public void inserir(Animal animal, Habitat habitat) {
        String sqlAnimal = "INSERT INTO animais (nome, especie, habitat_id) VALUES (?, ?, ?)";
        String sqlHabitat = "INSERT INTO habitats (nome, descricao) VALUES (?, ?) RETURNING id";

        try (Connection conexao = ConexaoPostgreSQL.conectar();
             PreparedStatement stmtHabitat = conexao.prepareStatement(sqlHabitat);
             PreparedStatement stmtAnimal = conexao.prepareStatement(sqlAnimal)) {

            // Inserindo habitat e recuperando o ID
            stmtHabitat.setString(1, habitat.getNome());
            stmtHabitat.setString(2, habitat.getDescricao());
            ResultSet rs = stmtHabitat.executeQuery();

            int habitatId = 0;
            if (rs.next()) {
                habitatId = rs.getInt(1);
            }

            // Inserindo animal com o habitat associado
            stmtAnimal.setString(1, animal.getNome());
            stmtAnimal.setString(2, animal.getEspecie());
            stmtAnimal.setInt(3, habitatId);
            stmtAnimal.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir animal e habitat: " + e.getMessage(), e);
        }
    }

    public List<Animal> listar() {
        String sql = "SELECT a.nome AS animal_nome, a.especie, h.nome AS habitat_nome, h.descricao " +
                     "FROM animais a " +
                     "LEFT JOIN habitats h ON a.habitat_id = h.id";
        List<Animal> animais = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.conectar();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String animalNome = rs.getString("animal_nome");
                String especie = rs.getString("especie");
                String habitatNome = rs.getString("habitat_nome");
                String habitatDescricao = rs.getString("descricao");

                Habitat habitat = new Habitat(habitatNome, habitatDescricao);
                Animal animal = new Animal(animalNome, especie, habitat);
                animais.add(animal);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais: " + e.getMessage(), e);
        }

        return animais;
    }
}
