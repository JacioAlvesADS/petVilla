package database;

import java.util.ArrayList;
import java.util.List;

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
}