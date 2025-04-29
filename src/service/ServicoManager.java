package service;

import database.BancoDados;
import java.time.LocalDateTime;
import model.Agendamento;
import model.Servico;
import model.StatusServico;

public class ServicoManager {

    public void cadastrarServico(String nome, String descricao, double preco, int duracaoMinutos) {
        if (preco < 0 || duracaoMinutos <= 0) {
            System.out.println("❌ Preço ou duração inválidos. Tente novamente.");
            return;
        }
        BancoDados.servicos.add(new Servico(nome, descricao, preco, duracaoMinutos));
        System.out.println("✅ Serviço cadastrado com sucesso!");
    }

    public void atualizarStatusAgendamento(String nomeDono, String nomePet, LocalDateTime dataHora, StatusServico novoStatus) {
    for (Agendamento agendamento : BancoDados.agendamentos) {
        if (agendamento.getNomeDono().equalsIgnoreCase(nomeDono) &&
            agendamento.getNomePet().equalsIgnoreCase(nomePet) &&
            agendamento.getDataHora().equals(dataHora)) {
            agendamento.setStatus(novoStatus);
            System.out.println("✅ Status atualizado com sucesso!");
            return;
        }
    }
    System.out.println("❌ Agendamento não encontrado.");
}

    public void consultarStatusAgendamento(String nomeDono, String nomePet) {
        boolean encontrou = false;
        System.out.println("\n📋 Status dos Agendamentos:");
        for (Agendamento agendamento : BancoDados.agendamentos) {
            if (agendamento.getNomeDono().equalsIgnoreCase(nomeDono) &&
                agendamento.getNomePet().equalsIgnoreCase(nomePet)) {
                System.out.println(agendamento);
                encontrou = true;
        }
    }
            if (!encontrou) {
                System.out.println("❌ Nenhum agendamento encontrado para este dono e pet.");
    }
}

    public void editarServico(String nome, double novoPreco, int novaDuracao) {
        for (Servico s : BancoDados.servicos) {
            if (s.getNome().equalsIgnoreCase(nome)) {
                s.setPreco(novoPreco);
                s.setDuracaoMinutos(novaDuracao);
                System.out.println("✅ Serviço atualizado com sucesso!");
                return;
            }
        }
        System.out.println("❌ Serviço não encontrado.");
    }

    public void excluirServico(String nome) {
        for (Servico s : BancoDados.servicos) {
            if (s.getNome().equalsIgnoreCase(nome)) {
                boolean temAgendamentos = BancoDados.agendamentos.stream()
                    .anyMatch(a -> a.getNomeServico().equalsIgnoreCase(nome));
                if (temAgendamentos) {
                    System.out.println("❌ Este serviço possui agendamentos futuros e não pode ser excluído.");
                    return;
                }
                BancoDados.servicos.remove(s);
                System.out.println("✅ Serviço excluído com sucesso.");
                return;
            }
        }
        System.out.println("❌ Serviço não encontrado.");
    }

    public void listarServicos() {
        System.out.println("\n📋 Lista de Serviços:");
        if (BancoDados.servicos.isEmpty()) {
            System.out.println("⚠️ Nenhum serviço cadastrado.");
        } else {
            for (Servico s : BancoDados.servicos) {
                System.out.println(s);
                boolean temAgendamentos = BancoDados.agendamentos.stream()
                    .anyMatch(a -> a.getNomeServico().equalsIgnoreCase(s.getNome()));
                if (temAgendamentos) {
                    System.out.println("🔔 Agendamentos:");
                    BancoDados.agendamentos.stream()
                        .filter(a -> a.getNomeServico().equalsIgnoreCase(s.getNome()))
                        .forEach(a -> System.out.println("   - " + a));
                } else {
                    System.out.println("   (Sem agendamentos)");
                }
            }
        }
    }

    public void adicionarAgendamento(String nomeServico, String nomeDono, String nomePet, LocalDateTime dataHora) {
        boolean servicoExiste = BancoDados.servicos.stream()
                .anyMatch(s -> s.getNome().equalsIgnoreCase(nomeServico));

        if (!servicoExiste) {
            System.out.println("❌ Serviço não encontrado.");
            return;
        }

        BancoDados.agendamentos.add(new Agendamento(nomeServico, nomeDono, nomePet, dataHora));
        System.out.println("✅ Agendamento registrado com sucesso!");
    }
}
