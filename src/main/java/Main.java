import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.Dono;
import model.Pet;
import model.StatusServico;
import service.CadastroService;
import service.ServicoManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoManager servicoManager = new ServicoManager();

        int opcaoPrincipal;
        do {
            System.out.println("\n🐾 Sistema de Agendamento - Petshop 🐾");
            System.out.println("1 - Acesso como Cliente");
            System.out.println("2 - Acesso como Funcionário/Admin");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoPrincipal) {
                case 1:
                    menuCliente(scanner, servicoManager);
                    break;
                case 2:
                    menuFuncionario(scanner, servicoManager);
                    break;
                case 0:
                    System.out.println("👋 Saindo...");
                    break;
                default:
                    System.out.println("❌ Opção inválida.");
            }

        } while (opcaoPrincipal != 0);

        scanner.close();
    }

    public static void menuCliente(Scanner scanner, ServicoManager servicoManager) {
        int opcao;
        do {
            System.out.println("\n👤 Menu Cliente");
            System.out.println("1 - Cadastrar Tutor");
            System.out.println("2 - Cadastrar Pet");
            System.out.println("3 - Listar Tutores");
            System.out.println("4 - Listar Pets");
            System.out.println("5 - Agendar Serviço");
            System.out.println("6 - Consultar Status do Agendamento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

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
                    CadastroService.cadastrarDono(new Dono(nome, email, telefone, endereco));
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
                    CadastroService.cadastrarPet(new Pet(nomePet, especie, raca, idade, historico));
                    break;

                case 3:
                    CadastroService.listarDonos();
                    break;

                case 4:
                    CadastroService.listarPets();
                    break;

                case 5:
                    System.out.print("Nome do Serviço para Agendamento: ");
                    String servicoAgendar = scanner.nextLine();
                    System.out.print("Nome do Tutor: ");
                    String donoAgendamento = scanner.nextLine();
                    System.out.print("Nome do Pet: ");
                    String petAgendamento = scanner.nextLine();
                    System.out.print("Data e Hora (dd/MM/yyyy HH:mm): ");
                    String dataHoraStr = scanner.nextLine().trim();

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
                        servicoManager.adicionarAgendamento(servicoAgendar, donoAgendamento, petAgendamento, dataHora);
                    } catch (DateTimeParseException e) {
                        System.out.println("❌ Data/Hora inválida. Use o formato: dd/MM/yyyy HH:mm");
                    }
                    break;

                case 6:
                    System.out.print("Nome do Tutor: ");
                    String donoConsulta = scanner.nextLine();
                    System.out.print("Nome do Pet: ");
                    String petConsulta = scanner.nextLine();
                    servicoManager.consultarStatusAgendamento(donoConsulta, petConsulta);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("❌ Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void menuFuncionario(Scanner scanner, ServicoManager servicoManager) {
        int opcao;
        do {
            System.out.println("\n🛠️ Menu Funcionário/Admin");
            System.out.println("1 - Cadastrar Serviço");
            System.out.println("2 - Editar Serviço");
            System.out.println("3 - Excluir Serviço");
            System.out.println("4 - Listar Serviços");
            System.out.println("5 - Atualizar Status do Agendamento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Serviço: ");
                    String nomeServico = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Preço (R$): ");
                    double preco = scanner.nextDouble();
                    System.out.print("Duração (min): ");
                    int duracao = scanner.nextInt();
                    scanner.nextLine();
                    servicoManager.cadastrarServico(nomeServico, descricao, preco, duracao);
                    break;

                case 2:
                    System.out.print("Nome do Serviço a Editar: ");
                    String nomeEditar = scanner.nextLine();
                    System.out.print("Novo Preço (R$): ");
                    double novoPreco = scanner.nextDouble();
                    System.out.print("Nova Duração (min): ");
                    int novaDuracao = scanner.nextInt();
                    scanner.nextLine();
                    servicoManager.editarServico(nomeEditar, novoPreco, novaDuracao);
                    break;

                case 3:
                    System.out.print("Nome do Serviço a Excluir: ");
                    String nomeExcluir = scanner.nextLine();
                    servicoManager.excluirServico(nomeExcluir);
                    break;

                case 4:
                    servicoManager.listarServicos();
                    break;

                case 5:
                    System.out.print("Nome do Tutor: ");
                    String donoStatus = scanner.nextLine();
                    System.out.print("Nome do Pet: ");
                    String petStatus = scanner.nextLine();
                    System.out.print("Data e Hora do Agendamento (dd/MM/yyyy HH:mm): ");
                    String dataHoraStatus = scanner.nextLine();

                    System.out.println("\nEscolha o novo status:");
                    System.out.println("1 - Aguardando atendimento");
                    System.out.println("2 - Em andamento");
                    System.out.println("3 - Finalizado");
                    int statusChoice = scanner.nextInt();
                    scanner.nextLine();

                    StatusServico novoStatus;
                    switch (statusChoice) {
                        case 1:
                            novoStatus = StatusServico.AGUARDANDO_ATENDIMENTO;
                            break;
                        case 2:
                            novoStatus = StatusServico.EM_ANDAMENTO;
                            break;
                        case 3:
                            novoStatus = StatusServico.FINALIZADO;
                            break;
                        default:
                            System.out.println("❌ Opção inválida.");
                            continue;
                    }

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStatus, formatter);
                        servicoManager.atualizarStatusAgendamento(donoStatus, petStatus, dataHora, novoStatus);
                    } catch (DateTimeParseException e) {
                        System.out.println("❌ Data/Hora inválida. Use o formato: dd/MM/yyyy HH:mm");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("❌ Opção inválida.");
            }

        } while (opcao != 0);
    }
}