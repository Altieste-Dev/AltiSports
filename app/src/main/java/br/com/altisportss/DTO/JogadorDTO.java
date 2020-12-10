package br.com.altisportss.DTO;

import java.io.Serializable;

import br.com.altisportss.model.Jogador;
import br.com.altisportss.model.bo.Posicao;

public class JogadorDTO implements Serializable {

    private int id;
    private String Nome;
    private String DataNascimento;
    private String Posicao;
    private String Cpf;

    public JogadorDTO() {
    }

    public JogadorDTO( int id, String nome, String dataNascimento, String posicao, String cpf) {
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

    public Jogador getJogador(){
        Jogador a = new Jogador();
        a.setId(this.id);
        a.setNome(this.Nome);
        a.setDataNascimento(this.DataNascimento);
        a.setPosicao(this.Posicao);
        a.setCpf(this.Cpf);
        return a;
    }
}
