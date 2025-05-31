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
        System.out.println("\n┌────────────────────────────┐");
        System.out.println("│      TUTORES CADASTRADOS   │");
        System.out.println("└────────────────────────────┘");

        if (BancoDados.donos.isEmpty()) {
            System.out.println("⚠️ Nenhum tutor cadastrado.");
            return;
        }

        for (int i = 0; i < BancoDados.donos.size(); i++) {
            Dono d = BancoDados.donos.get(i);
            System.out.println("👤 ID: " + (i + 1));
            System.out.println("│ Nome: " + d.getNome());
            System.out.println("│ Email: " + d.getEmail());
            System.out.println("│ Telefone: " + d.getTelefone());
            System.out.println("│ Endereço: " + d.getEndereco());
            System.out.println("├────────────────────────────┤");
        }
    }

    public static void listarPets() {
        System.out.println("\n┌───────────────────────────┐");
        System.out.println("│      PETS CADASTRADOS     │");
        System.out.println("└───────────────────────────┘");

        if (BancoDados.pets.isEmpty()) {
            System.out.println("⚠️ Nenhum pet cadastrado.");
            return;
        }

        for (int i = 0; i < BancoDados.pets.size(); i++) {
            Pet p = BancoDados.pets.get(i);
            System.out.println("🐾 ID: " + (i + 1));
            System.out.println("│ Nome: " + p.getNome());
            System.out.println("│ Espécie: " + p.getEspecie());
            System.out.println("│ Raça: " + p.getRaca());
            System.out.println("│ Idade: " + p.getIdade() + " anos");
            System.out
                    .println("│ Histórico: " + (p.getHistoricoMedico().isEmpty() ? "Nenhum" : p.getHistoricoMedico()));
            System.out.println("├───────────────────────────┤");
        }
    }
}
