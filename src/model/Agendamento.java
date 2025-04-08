package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Agendamento {
    private String nomeServico;
    private String nomeDono;
    private String nomePet;
    private LocalDateTime dataHora;

    public Agendamento(String nomeServico, String nomeDono, String nomePet, LocalDateTime dataHora) {
        this.nomeServico = nomeServico;
        this.nomeDono = nomeDono;
        this.nomePet = nomePet;
        this.dataHora = dataHora;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public String getNomePet() {
        return nomePet;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    return "📅 Agendamento: " + nomeServico +
           " | Dono: " + nomeDono +
           " | Pet: " + nomePet +
           " | Data: " + dataHora.format(formatoData) +
           " | Horário: " + dataHora.format(formatoHora);
    }
}
