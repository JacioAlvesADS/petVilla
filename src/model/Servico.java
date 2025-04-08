package model;

public class Servico {
    private String nome;
    private String descricao;
    private double preco;
    private int duracaoMinutos;

    public Servico(String nome, String descricao, double preco, int duracaoMinutos) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public int getDuracaoMinutos() { return duracaoMinutos; }

    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    @Override
    public String toString() {
        return "Serviço: " + nome + ", Preço: R$" + preco + ", Duração: " + duracaoMinutos + " min";
    }
}
