package br.com.altisportss.model;

public class Jogador {
    private int id;
    private String Nome;
    private String DataNascimento;
    private String Posicao;
    private String Cpf;

    public Jogador() {
    }

    public Jogador(int id, String nome, String dataNascimento, String posicao, String cpf) {
        id = id;
        Nome = nome;
        DataNascimento = dataNascimento;
        Posicao = posicao;
        Cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getPosicao() {
        return Posicao;
    }

    public void setPosicao(String posicao) {
        Posicao = posicao;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }
}
