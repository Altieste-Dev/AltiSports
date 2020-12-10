package br.com.altisportss.service;

import java.util.Arrays;
import java.util.List;

import br.com.altisportss.model.Produto;

public class ProdutoService {


    public List<Produto> carregarProdutos() {

        Produto camisa_liverpool = new Produto(1, "Camisa Liverpool Home 20/21", 285.00, true);
        Produto camisa_realmadrid = new Produto(2, "Camisa Real Madrid Home 20/21", 289.00, true);
        Produto camisa_chelsea = new Produto(3, "Camisa Chelsea Home 19/20 ", 190.00, true);
        Produto tenis_adidas = new Produto(4, "Tênis Adidas Boost", 250.0, true);
        Produto tenis_nike = new Produto(5, "Tênis Nike Run", 299.50, true);
        Produto chuteira_nike = new Produto(6, "Chuteira Nike Mercurial Vapor Campo", 350.00, true);

        return Arrays.asList(camisa_liverpool, camisa_realmadrid, camisa_chelsea, tenis_adidas, tenis_nike, chuteira_nike);
    }

}
