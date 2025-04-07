package service;

import model.Dono;
import model.Pet;
import database.BancoDados;

public class CadastroService {
    public static void cadastrarDono(Dono dono) {
        BancoDados.donos.add(dono);
        System.out.println("✅ Dono cadastrado com sucesso!");
    }

    public static void cadastrarPet(Pet pet) {
        BancoDados.pets.add(pet);
        System.out.println("✅ Pet cadastrado com sucesso!");
    }

    public static void listarDonos() {
        System.out.println("\n📋 Lista de Donos:");
        for (Dono d : BancoDados.donos) {
            System.out.println(d);
        }
    }

    public static void listarPets() {
        System.out.println("\n📋 Lista de Pets:");
        for (Pet p : BancoDados.pets) {
            System.out.println(p);
        }
    }
}
