package service;

import database.BancoDados;
import model.Dono;
import model.Pet;

public class CadastroService {
    public static void cadastrarDono(Dono dono) {
        BancoDados.donos.add(dono);
        System.out.println("(+) Tutor cadastrado com sucesso!");
    }

    public static void cadastrarPet(Pet pet) {
        BancoDados.pets.add(pet);
        System.out.println("(+) Pet cadastrado com sucesso!");
    }

    public static void listarDonos() {
        BancoDados.carregarDados();
        
        System.out.println("\n=== Lista de Donos ===");
        if (BancoDados.donos.isEmpty()) {
            System.out.println("(!) Nenhum tutor cadastrado.");
        } else {
            for (int i = 0; i < BancoDados.donos.size(); i++) {
                System.out.println((i + 1) + " - " + BancoDados.donos.get(i));
            }
        }
    }

    public static void listarPets() {
        BancoDados.carregarDados();
        
        System.out.println("\n=== Lista de Pets ===");
        if (BancoDados.pets.isEmpty()) {
            System.out.println("(!) Nenhum pet cadastrado.");
        } else {
            for (int i = 0; i < BancoDados.pets.size(); i++) {
                Pet p = BancoDados.pets.get(i);
                System.out.println((i + 1) + " - " + p);
            }
        }
    }
}
