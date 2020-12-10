package br.com.altisportss.dao;

import android.content.Context;

import br.com.altisportss.dao.helpers.DaoHelper;
import br.com.altisportss.model.CarrinhoItem;

public class CarrinhoItemDao extends DaoHelper<CarrinhoItem> {

    public CarrinhoItemDao(Context c) {
        super(c, CarrinhoItem.class);
    }
}
