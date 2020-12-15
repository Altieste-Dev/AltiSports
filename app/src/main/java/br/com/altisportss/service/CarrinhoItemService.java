package br.com.altisportss.service;

import br.com.altisportss.model.dao.CarrinhoItemDao;
import br.com.altisportss.model.vo.Carrinho;
import br.com.altisportss.model.vo.CarrinhoItem;
import br.com.altisportss.model.vo.Produto;

public class CarrinhoItemService {

    private CarrinhoItemDao carrinhoItemDao;

    public CarrinhoItem criarItem(Carrinho carrinho, Produto produto, Integer quantidade) {
        return new CarrinhoItem(produto, quantidade, quantidade * produto.getValor(), carrinho);
    }

    public CarrinhoItem adicionarMaisUm(CarrinhoItem carrinhoItem) {
        carrinhoItem.setQuantidade(carrinhoItem.getQuantidade() + 1);
        carrinhoItem.setSubtotal(carrinhoItem.getQuantidade() * carrinhoItem.getProduto().getValor());
        return carrinhoItem;

    }

    public CarrinhoItem subtrairMenosUm(CarrinhoItem carrinhoItem) {
        carrinhoItem.setQuantidade(carrinhoItem.getQuantidade() - 1);
        carrinhoItem.setSubtotal(carrinhoItem.getQuantidade() * carrinhoItem.getProduto().getValor());
        return carrinhoItem;
    }
}
