package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import database.BancoDados;
import model.Agendamento;
import model.Servico;
import model.StatusServico;

public class ServicoManager {

    public void cadastrarServico(String nome, String descricao, double preco, int duracaoMinutos) {
        if (preco < 0 || duracaoMinutos <= 0) {
            System.out.println("(X) Preço ou duração inválidos. Tente novamente.");
            return;
        }
        BancoDados.servicos.add(new Servico(nome, descricao, preco, duracaoMinutos));
        System.out.println("(+) Serviço cadastrado com sucesso!");
    }

    public void atualizarStatusAgendamento(String nomeDono, String nomePet, LocalDateTime dataHora,
            StatusServico novoStatus) {
        for (Agendamento agendamento : BancoDados.agendamentos) {
            if (agendamento.getNomeDono().equalsIgnoreCase(nomeDono) &&
                    agendamento.getNomePet().equalsIgnoreCase(nomePet) &&
                    agendamento.getDataHora().equals(dataHora)) {
                agendamento.setStatus(novoStatus);
                BancoDados.salvarDados();
                System.out.println("(+) Status atualizado com sucesso!");
                return;
            }
        }
        System.out.println("(X) Agendamento não encontrado.");
    }

    public void consultarStatusAgendamento(String nomeDono, String nomePet) {
        boolean encontrou = false;
        System.out.println("\n=== Status dos Agendamentos ===");
        for (Agendamento agendamento : BancoDados.agendamentos) {
            if (agendamento.getNomeDono().equalsIgnoreCase(nomeDono) &&
                    agendamento.getNomePet().equalsIgnoreCase(nomePet)) {
                System.out.println(agendamento);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("(X) Nenhum agendamento encontrado para este dono e pet.");
        }
    }

    public void editarServico(int indice, double novoPreco, int novaDuracao) {
        if (indice < 1 || indice > BancoDados.servicos.size()) {
            System.out.println("(X) Índice inválido.");
            return;
        }

        Servico s = BancoDados.servicos.get(indice - 1);
        s.setPreco(novoPreco);
        s.setDuracaoMinutos(novaDuracao);
        System.out.println("(+) Serviço atualizado com sucesso!");
    }

    public void excluirServico(int indice) {
        if (indice < 1 || indice > BancoDados.servicos.size()) {
            System.out.println("(X) Índice inválido.");
            return;
        }

        Servico s = BancoDados.servicos.get(indice - 1);

        boolean temAgendamentosAtivos = BancoDados.agendamentos.stream()
                .anyMatch(a -> a.getNomeServico().equalsIgnoreCase(s.getNome()) &&
                        a.getStatus() != StatusServico.FINALIZADO);

        if (temAgendamentosAtivos) {
            System.out.println("(X) Este serviço ainda possui agendamentos não finalizados e não pode ser excluído.");
            return;
        }

        BancoDados.servicos.remove(s);
        System.out.println("(+) Serviço excluído com sucesso.");
    }

    public void listarServicos() {
        System.out.println("\n┌───────────────────────────┐");
        System.out.println("│    SERVIÇOS CADASTRADOS   │");
        System.out.println("└───────────────────────────┘");

        if (BancoDados.servicos.isEmpty()) {
            System.out.println("⚠️ Nenhum serviço cadastrado.");
            return;
        }

        for (int i = 0; i < BancoDados.servicos.size(); i++) {
            Servico s = BancoDados.servicos.get(i);
            System.out.println("🛠️ ID: " + (i + 1));
            System.out.println("│ Nome: " + s.getNome());
            System.out.println("│ Descrição: " + s.getDescricao());
            System.out.println("│ Preço: R$" + s.getPreco());
            System.out.println("│ Duração: " + s.getDuracaoMinutos() + " min");
            System.out.println("├───────────────────────────┤");
        }
    }

    public boolean adicionarAgendamento(String nomeServico, String nomeDono, String nomePet, LocalDateTime dataHora) {
        boolean servicoExiste = BancoDados.servicos.stream()
                .anyMatch(s -> s.getNome().equalsIgnoreCase(nomeServico));

        if (!servicoExiste) {
            return false;
        }

        BancoDados.agendamentos.add(new Agendamento(nomeServico, nomeDono, nomePet, dataHora));
        return true;
    }

    public void listarAgendamentosComStatus() {
        System.out.println("\n┌────────────────────────────┐");
        System.out.println("│    AGENDAMENTOS CADASTRADOS│");
        System.out.println("└────────────────────────────┘");

        if (BancoDados.agendamentos.isEmpty()) {
            System.out.println("⚠️ Nenhum agendamento cadastrado.");
            return;
        }

        for (int i = 0; i < BancoDados.agendamentos.size(); i++) {
            Agendamento a = BancoDados.agendamentos.get(i);
            System.out.println("📅 ID: " + (i + 1));
            System.out.println("│ Serviço: " + a.getNomeServico());
            System.out.println("│ Dono: " + a.getNomeDono());
            System.out.println("│ Pet: " + a.getNomePet());
            System.out
                    .println("│ Data/Hora: " + a.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            System.out.println("│ Status: " + a.getStatus());
            System.out.println("├────────────────────────────┤");
        }
    }

    public void listarServicosNumerados() {
        System.out.println("\n=== Serviços cadastrados ===");
        if (BancoDados.servicos.isEmpty()) {
            System.out.println("(!) Nenhum serviço cadastrado.");
            return;
        }

        for (int i = 0; i < BancoDados.servicos.size(); i++) {
            Servico s = BancoDados.servicos.get(i);
            System.out.println((i + 1) + ". Serviço: " + s.getNome()
                    + " | Preço: R$" + s.getPreco()
                    + " | Duração: " + s.getDuracaoMinutos() + " min");
        }
    }
}