package br.com.altisportss.model.bo;

public class Posicao {
    private String nomePosicao;
    private String sigla;

    public Posicao(String nomePosicao, String sigla) {
        this.nomePosicao = nomePosicao;
        this.sigla = sigla;
    }

    public String getNomePosicao() {
        return nomePosicao;
    }

    public String getSigla() {
        return sigla;
    }
}
