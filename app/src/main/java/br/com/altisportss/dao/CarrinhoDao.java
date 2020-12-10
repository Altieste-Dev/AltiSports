package br.com.altisportss.dao;

import android.content.Context;

import br.com.altisportss.dao.helpers.DaoHelper;
import br.com.altisportss.model.Carrinho;

public class CarrinhoDao extends DaoHelper<Carrinho> {

    public CarrinhoDao(Context c) {
        super(c, Carrinho.class);
    }
}
