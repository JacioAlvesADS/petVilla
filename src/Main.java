import java.util.Scanner;
import model.Dono;
import model.Pet;
import service.CadastroService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n🐾 Sistema de Agendamento - Petshop 🐾");
            System.out.println("1 - Cadastrar Dono");
            System.out.println("2 - Cadastrar Pet");
            System.out.println("3 - Listar Donos");
            System.out.println("4 - Listar Pets");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();

                    Dono dono = new Dono(nome, email, telefone, endereco);
                    CadastroService.cadastrarDono(dono);
                    break;

                case 2:
                    System.out.print("Nome do Pet: ");
                    String nomePet = scanner.nextLine();
                    System.out.print("Espécie: ");
                    String especie = scanner.nextLine();
                    System.out.print("Raça: ");
                    String raca = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Histórico Médico (opcional): ");
                    String historico = scanner.nextLine();

                    Pet pet = new Pet(nomePet, especie, raca, idade, historico);
                    CadastroService.cadastrarPet(pet);
                    break;

                case 3:
                    CadastroService.listarDonos();
                    break;

                case 4:
                    CadastroService.listarPets();
                    break;

                case 0:
                    System.out.println("👋 Saindo...");
                    break;

                default:
                    System.out.println("⚠️ Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
