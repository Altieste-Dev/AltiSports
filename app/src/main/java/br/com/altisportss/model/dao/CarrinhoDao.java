package br.com.altisportss.model.dao;

import android.content.Context;

import br.com.altisportss.model.dao.helpers.DaoHelper;
import br.com.altisportss.model.vo.Carrinho;

public class CarrinhoDao extends DaoHelper<Carrinho> {

    public CarrinhoDao(Context c) {
        super(c, Carrinho.class);
    }
}
