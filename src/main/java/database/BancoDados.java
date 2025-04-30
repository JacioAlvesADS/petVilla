package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Agendamento;
import model.Dono;
import model.Pet;
import model.Servico;

public class BancoDados {
    public static List<Dono> donos = new ArrayList<>();
    public static List<Pet> pets = new ArrayList<>();
    public static List<Servico> servicos = new ArrayList<>();
    public static Map<Servico, List<String>> agendamentosFuturos = new HashMap<>();
    public static List<Agendamento> agendamentos = new ArrayList<>();

}