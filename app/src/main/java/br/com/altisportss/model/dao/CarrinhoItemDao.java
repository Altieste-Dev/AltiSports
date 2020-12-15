package br.com.altisportss.model.dao;

import android.content.Context;

import br.com.altisportss.model.dao.helpers.DaoHelper;
import br.com.altisportss.model.vo.CarrinhoItem;

public class CarrinhoItemDao extends DaoHelper<CarrinhoItem> {

    public CarrinhoItemDao(Context c) {
        super(c, CarrinhoItem.class);
    }
}
