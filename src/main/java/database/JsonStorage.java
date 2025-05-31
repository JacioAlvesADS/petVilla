package database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Agendamento;
import model.Dono;
import model.Pet;
import model.Servico;

public class JsonStorage {
    private static final String RESOURCES_PATH = "petVilla/data/";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public static void salvarDonos(List<Dono> donos) {
        salvarParaArquivo(donos, RESOURCES_PATH + "donos.json");
    }

    public static List<Dono> carregarDonos() {
        Type tipoLista = new TypeToken<ArrayList<Dono>>(){}.getType();
        return carregarDeArquivo(RESOURCES_PATH + "donos.json", tipoLista, new ArrayList<>());
    }

    public static void salvarPets(List<Pet> pets) {
        salvarParaArquivo(pets, RESOURCES_PATH + "pets.json");
    }

    public static List<Pet> carregarPets() {
        Type tipoLista = new TypeToken<ArrayList<Pet>>(){}.getType();
        return carregarDeArquivo(RESOURCES_PATH + "pets.json", tipoLista, new ArrayList<>());
    }

    public static void salvarServicos(List<Servico> servicos) {
        salvarParaArquivo(servicos, RESOURCES_PATH + "servicos.json");
    }

    public static List<Servico> carregarServicos() {
        Type tipoLista = new TypeToken<ArrayList<Servico>>(){}.getType();
        return carregarDeArquivo(RESOURCES_PATH + "servicos.json", tipoLista, new ArrayList<>());
    }

    public static void salvarAgendamentos(List<Agendamento> agendamentos) {
        salvarParaArquivo(agendamentos, RESOURCES_PATH + "agendamentos.json");
    }

    public static List<Agendamento> carregarAgendamentos() {
        Type tipoLista = new TypeToken<ArrayList<Agendamento>>(){}.getType();
        return carregarDeArquivo(RESOURCES_PATH + "agendamentos.json", tipoLista, new ArrayList<>());
    }

    private static <T> void salvarParaArquivo(T objeto, String caminhoArquivo) {
        try {
            String json = gson.toJson(objeto);
            try (Writer writer = new OutputStreamWriter(
                    new FileOutputStream(caminhoArquivo), StandardCharsets.UTF_8)) {
                writer.write(json);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    private static <T> T carregarDeArquivo(String caminhoArquivo, Type tipo, T valorPadrao) {
        try {
            if (!Files.exists(Paths.get(caminhoArquivo))) {
                return valorPadrao;
            }
            String json = Files.readString(Paths.get(caminhoArquivo), StandardCharsets.UTF_8);
            return gson.fromJson(json, tipo);
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
            return valorPadrao;
        }
    }
} 