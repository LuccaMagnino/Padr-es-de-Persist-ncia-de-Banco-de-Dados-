package com.ufg.projeto.gerenciador;

import com.ufg.projeto.modelos.Habitat;
import com.ufg.projeto.dao.AnimalDAO;
import com.ufg.projeto.modelos.Animal;

import java.util.List;
import java.util.Scanner;

public class GerenciadorAnimais {
    private AnimalDAO animalDAO;

    public GerenciadorAnimais() {
        this.animalDAO = new AnimalDAO();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarAnimal(scanner);
                    break;
                case 2:
                    listarAnimais();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 3);

        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Adicionar Animal");
        System.out.println("2. Listar Animais");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void adicionarAnimal(Scanner scanner) {
        System.out.print("Nome do animal: ");
        String nome = scanner.nextLine();
    
        System.out.print("Espécie do animal: ");
        String especie = scanner.nextLine();
    
        System.out.print("Nome do habitat: ");
        String nomeHabitat = scanner.nextLine();
    
        System.out.print("Descrição do habitat: ");
        String descricaoHabitat = scanner.nextLine();
    
        Habitat habitat = new Habitat(nomeHabitat, descricaoHabitat);
        Animal animal = new Animal(nome, especie);
    
        animalDAO.inserir(animal, habitat);
        System.out.println("Animal e habitat adicionados ao banco de dados com sucesso!");
    }
    

    private void listarAnimais() {
        List<Animal> animais = animalDAO.listar();

        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            System.out.println("\n=== Lista de Animais ===");
            animais.forEach(System.out::println);
        }
    }
}
