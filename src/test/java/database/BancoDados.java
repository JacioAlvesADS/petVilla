package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Agendamento;
import model.Dono;
import model.Pet;
import model.Servico;

public class BancoDados {
    public static List<Dono> donos = new ArrayList<>();
    public static List<Pet> pets = new ArrayList<>();
    public static List<Servico> servicos = new ArrayList<>();
    public static List<Agendamento> agendamentos = new ArrayList<>();

    static {
        carregarDados();
    }

    public static void carregarDados() {
        donos = JsonStorage.carregarDonos();
        pets = JsonStorage.carregarPets();
        servicos = JsonStorage.carregarServicos();
        agendamentos = JsonStorage.carregarAgendamentos();
    }

    public static void salvarDados() {
        JsonStorage.salvarDonos(donos);
        JsonStorage.salvarPets(pets);
        JsonStorage.salvarServicos(servicos);
        JsonStorage.salvarAgendamentos(agendamentos);
    }

    // --- Métodos de CRUD para Dono ---
    public static void addDono(Dono dono) {
        // Validação básica para evitar duplicatas por email
        if (donos.stream().noneMatch(d -> d.getEmail().equals(dono.getEmail()))) {
            donos.add(dono);
            salvarDados();
        } else {
            System.err.println("Erro: Dono com email " + dono.getEmail() + " já existe.");
        }
    }

    public static List<Dono> getAllDonos() {
        return new ArrayList<>(donos);
    }

    public static Optional<Dono> getDonoByEmail(String email) {
        return donos.stream().filter(dono -> dono.getEmail().equals(email)).findFirst();
    }

    public static boolean updateDono(Dono updatedDono) {
        for (int i = 0; i < donos.size(); i++) {
            if (donos.get(i).getEmail().equals(updatedDono.getEmail())) {
                donos.set(i, updatedDono);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    public static boolean deleteDono(String email) {
        boolean removed = donos.removeIf(dono -> dono.getEmail().equals(email));
        if (removed) {
            salvarDados();
        }
        return removed;
    }

    // --- Métodos de CRUD para Pet ---
    public static void addPet(Pet pet) {
        // Sem ID único explícito, adiciona pets mesmo com nomes duplicados
        // Se você quiser evitar duplicatas por nome, adicione a lógica aqui.
        pets.add(pet);
        salvarDados();
    }

    public static List<Pet> getAllPets() { return new ArrayList<>(pets); }

    // Busca por nome do Pet (assumindo que o nome pode ser usado para busca para testes)
    public static Optional<Pet> getPetByNome(String nome) {
        return pets.stream().filter(pet -> pet.getNome().equals(nome)).findFirst();
    }

    public static boolean updatePet(Pet updatedPet) {
        // Para atualizar, precisamos encontrar o pet original.
        // Como não há ID, vamos usar uma combinação de nome, espécie e raça para identificação.
        // Isso pode não ser totalmente único em um cenário real.
        for (int i = 0; i < pets.size(); i++) {
            Pet existingPet = pets.get(i);
            if (existingPet.getNome().equals(updatedPet.getNome()) &&
                existingPet.getEspecie().equals(updatedPet.getEspecie()) &&
                existingPet.getRaca().equals(updatedPet.getRaca())) {
                pets.set(i, updatedPet);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    public static boolean deletePet(String nome) {
        // Deleta o primeiro pet encontrado com o nome fornecido.
        // Se houver pets com nomes duplicados, apenas um será removido.
        boolean removed = pets.removeIf(pet -> pet.getNome().equals(nome));
        if (removed) {
            salvarDados();
        }
        return removed;
    }

    // --- Métodos de CRUD para Servico ---
    public static void addServico(Servico servico) {
        // Sem ID único explícito, adiciona serviços mesmo com nomes duplicados
        servicos.add(servico);
        salvarDados();
    }

    public static List<Servico> getAllServicos() { return new ArrayList<>(servicos); }

    // Busca por nome do Serviço
    public static Optional<Servico> getServicoByNome(String nome) {
        return servicos.stream().filter(servico -> servico.getNome().equals(nome)).findFirst();
    }

    public static boolean updateServico(Servico updatedServico) {
        // Para atualizar, usamos o nome como identificador.
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i).getNome().equals(updatedServico.getNome())) {
                servicos.set(i, updatedServico);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    public static boolean deleteServico(String nome) {
        boolean removed = servicos.removeIf(servico -> servico.getNome().equals(nome));
        if (removed) {
            salvarDados();
        }
        return removed;
    }

    // --- Métodos de CRUD para Agendamento ---
    public static void addAgendamento(Agendamento agendamento) {
        // Sem ID único explícito, adiciona agendamentos mesmo com detalhes duplicados
        agendamentos.add(agendamento);
        salvarDados();
    }

    public static List<Agendamento> getAllAgendamentos() { return new ArrayList<>(agendamentos); }

    // Busca por uma combinação de detalhes para identificar o agendamento
    public static Optional<Agendamento> getAgendamentoByDetails(String nomeServico, String nomeDono, String nomePet) {
        return agendamentos.stream()
                .filter(a -> a.getNomeServico().equals(nomeServico) &&
                             a.getNomeDono().equals(nomeDono) &&
                             a.getNomePet().equals(nomePet))
                .findFirst();
    }

    public static boolean updateAgendamento(Agendamento updatedAgendamento) {
        // Para atualizar, precisamos encontrar o agendamento original usando seus detalhes.
        for (int i = 0; i < agendamentos.size(); i++) {
            Agendamento existingAgendamento = agendamentos.get(i);
            if (existingAgendamento.getNomeServico().equals(updatedAgendamento.getNomeServico()) &&
                existingAgendamento.getNomeDono().equals(updatedAgendamento.getNomeDono()) &&
                existingAgendamento.getNomePet().equals(updatedAgendamento.getNomePet()) &&
                existingAgendamento.getDataHora().isEqual(updatedAgendamento.getDataHora())) {
                agendamentos.set(i, updatedAgendamento);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    public static boolean deleteAgendamento(String nomeServico, String nomeDono, String nomePet) {
        boolean removed = agendamentos.removeIf(a -> a.getNomeServico().equals(nomeServico) &&
                                                     a.getNomeDono().equals(nomeDono) &&
                                                     a.getNomePet().equals(nomePet));

        if (removed) {
            salvarDados();
        }
        return removed;
    }
}
