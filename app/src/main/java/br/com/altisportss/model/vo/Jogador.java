package br.com.altisportss.model.vo;

public class Jogador {
    private String id;
    private String nome;
    private String dataNascimento;
    private String posicao;
    private String cpf;

    public Jogador() {
    }

    public Jogador(int String, String nome, String dataNascimento, String posicao, String cpf) {
        id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.posicao = posicao;
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
