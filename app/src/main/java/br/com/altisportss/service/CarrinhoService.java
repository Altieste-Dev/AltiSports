package br.com.altisportss.service;

import java.util.ArrayList;

import br.com.altisportss.model.vo.Carrinho;
import br.com.altisportss.model.vo.CarrinhoItem;
import br.com.altisportss.model.vo.Produto;

public class CarrinhoService {

    private ProdutoService produtoService;
    private CarrinhoItemService carrinhoItemService;

    public CarrinhoService() {
        produtoService = new ProdutoService();
        carrinhoItemService = new CarrinhoItemService();

    }

    public Carrinho iniciarComanda() {
        return new Carrinho(1, 0.0, new ArrayList<>());
    }

    public Carrinho adicionarItemNaComanda(Carrinho carrinho, Produto produto, Integer quantidade) {
        CarrinhoItem carrinhoItem = carrinhoItemService.criarItem(carrinho, produto, quantidade);
        carrinho.getItems().add(carrinhoItem);
        carrinho = recalcularTotal(carrinho);
        return carrinho;
    }

    public Carrinho recalcularTotal(Carrinho carrinho) {
        Double valorTotal = 0.0;

        for (CarrinhoItem item : carrinho.getItems()) {
            valorTotal += item.getSubtotal();
        }

        carrinho.setValorTotal(valorTotal);
        return carrinho;
    }
}
