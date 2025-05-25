import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import database.BancoDados;
import model.Agendamento;
import model.Dono;
import model.Pet;
import model.StatusServico;
import service.CadastroService;
import service.ServicoManager;

public class Main {
    static {
        try {
            System.setProperty("file.encoding", "UTF-8");
            System.setProperty("console.encoding", "UTF-8");
        } catch (Exception e) {
            System.err.println("Erro ao configurar encoding: " + e.getMessage());
        }
    }

    public static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar o console.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        ServicoManager servicoManager = new ServicoManager();

        int opcaoPrincipal;
        do {
            limparConsole();
            System.out.println("\n=== Sistema de Agendamento - Petshop ===");
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
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("(X) Opção inválida.");
            }

        } while (opcaoPrincipal != 0);

        scanner.close();
    }

    public static void menuCliente(Scanner scanner, ServicoManager servicoManager) {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n=== Menu Cliente ===");
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
                    BancoDados.salvarDados();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
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
                    BancoDados.salvarDados();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 3:
                    CadastroService.listarDonos();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 4:
                    CadastroService.listarPets();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 5:
                    if (BancoDados.servicos.isEmpty()) {
                        System.out.println("(!) Nenhum serviço disponível para agendamento.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\n=== Serviços disponíveis ===");
                    for (int i = 0; i < BancoDados.servicos.size(); i++) {
                        System.out.println((i + 1) + " - " + BancoDados.servicos.get(i));
                    }

                    System.out.print("\nEscolha o número do serviço: ");
                    int indiceServico = scanner.nextInt();
                    scanner.nextLine();

                    if (indiceServico < 1 || indiceServico > BancoDados.servicos.size()) {
                        System.out.println("(X) Número inválido.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }

                    String nomeServicoEscolhido = BancoDados.servicos.get(indiceServico - 1).getNome();

                    System.out.print("Nome do Tutor: ");
                    String donoAgendamento = scanner.nextLine();

                    System.out.print("Nome do Pet: ");
                    String petAgendamento = scanner.nextLine();

                    System.out.print("Data e Hora (dd/MM/yyyy HH:mm): ");
                    String dataHoraStr = scanner.nextLine().trim();

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

                        servicoManager.adicionarAgendamento(nomeServicoEscolhido, donoAgendamento, petAgendamento,
                                dataHora);
                        BancoDados.salvarDados();
                        System.out.println("\n(+) Agendamento feito com sucesso!");
                    } catch (DateTimeParseException e) {
                        System.out.println("(X) Data/Hora inválida. Use o formato: dd/MM/yyyy HH:mm");
                    }

                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 6:
                    servicoManager.listarAgendamentosComStatus();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("(X) Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void menuFuncionario(Scanner scanner, ServicoManager servicoManager) {
        int opcao;
        do {
            limparConsole();
            System.out.println("\n=== Menu Funcionário/Admin ===");
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
                    BancoDados.salvarDados();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 2:
                    if (BancoDados.servicos.isEmpty()) {
                        System.out.println("(!) Nenhum serviço cadastrado.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\n=== Serviços cadastrados ===");
                    for (int i = 0; i < BancoDados.servicos.size(); i++) {
                        System.out.println((i + 1) + " - " + BancoDados.servicos.get(i));
                    }

                    System.out.print("\nEscolha o número do serviço a editar: ");
                    int indiceEditar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo Preço (R$): ");
                    double novoPreco = scanner.nextDouble();

                    System.out.print("Nova Duração (min): ");
                    int novaDuracao = scanner.nextInt();
                    scanner.nextLine();

                    servicoManager.editarServico(indiceEditar, novoPreco, novaDuracao);
                    BancoDados.salvarDados();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 3:
                    if (BancoDados.servicos.isEmpty()) {
                        System.out.println("(!) Nenhum serviço cadastrado.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\n=== Lista de Serviços ===");
                    for (int i = 0; i < BancoDados.servicos.size(); i++) {
                        System.out.println((i + 1) + ". " + BancoDados.servicos.get(i));
                    }

                    System.out.print("Digite o número do serviço que deseja excluir: ");
                    int indiceExcluir = scanner.nextInt();
                    scanner.nextLine();

                    servicoManager.excluirServico(indiceExcluir);
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 4:
                    servicoManager.listarServicos();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 5:
                    if (BancoDados.agendamentos.isEmpty()) {
                        System.out.println("(!) Nenhum agendamento disponível.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\n=== Lista de Agendamentos ===");
                    for (int i = 0; i < BancoDados.agendamentos.size(); i++) {
                        System.out.println((i + 1) + " - " + BancoDados.agendamentos.get(i));
                    }

                    System.out.print("\nEscolha o número do agendamento para atualizar: ");
                    int indiceAgendamento = scanner.nextInt();
                    scanner.nextLine();

                    if (indiceAgendamento < 1 || indiceAgendamento > BancoDados.agendamentos.size()) {
                        System.out.println("(X) Número inválido.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }

                    Agendamento ag = BancoDados.agendamentos.get(indiceAgendamento - 1);

                    System.out.println("\nEscolha o novo status:");
                    System.out.println("1 - Aguardando atendimento");
                    System.out.println("2 - Em andamento");
                    System.out.println("3 - Finalizado");
                    int statusChoice = scanner.nextInt();
                    scanner.nextLine();

                    StatusServico novoStatus = null;
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
                            System.out.println("(X) Opção inválida.");
                            System.out.println("\nPressione Enter para continuar...");
                            scanner.nextLine();
                            break;
                    }

                    ag.setStatus(novoStatus);
                    BancoDados.salvarDados();
                    System.out.println("\n(+) Status atualizado com sucesso!");
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("(X) Opção inválida.");
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    return;
            }

        } while (opcao != 0);
    }
}