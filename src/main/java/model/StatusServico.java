package model;

public enum StatusServico {
    AGUARDANDO_ATENDIMENTO("Aguardando atendimento"),
    EM_ANDAMENTO("Em andamento"),
    FINALIZADO("Finalizado");

    private final String descricao;

    StatusServico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}