package model;

public class Pet {
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String historicoMedico;

    public Pet(String nome, String especie, String raca, int idade, String historicoMedico) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.historicoMedico = historicoMedico;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    @Override
    public String toString() {
        return "Pet: " + nome + ", Espécie: " + especie + ", Raça: " + raca + ", Idade: " + idade + ", Histórico Médico: " + historicoMedico;
    }
}
