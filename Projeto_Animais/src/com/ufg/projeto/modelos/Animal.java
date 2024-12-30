package com.ufg.projeto.modelos;

public class Animal {
    private String nome;
    private String especie;
    private Habitat habitat;

    public Animal(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
    }

    public Animal(String nome, String especie, Habitat habitat) {
        this.nome = nome;
        this.especie = especie;
        this.habitat = habitat;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", habitat=" + habitat +
                '}';
    }
}
