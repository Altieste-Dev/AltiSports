package br.com.altisportss.controller;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.sql.SQLException;

import br.com.altisportss.R;
import br.com.altisportss.model.dao.CarrinhoDao;
import br.com.altisportss.model.dao.ProdutoDao;
import br.com.altisportss.model.vo.Produto;
import br.com.altisportss.service.CarrinhoService;
import br.com.altisportss.view.CarrinhoItemActivity;

public class CarrinhoItemController {

    private CarrinhoItemActivity activity;

    private ArrayAdapter<Produto> adapterProdutos;

    private ProdutoDao produtoDao;
    private CarrinhoDao carrinhoDao;

    private CarrinhoService carrinhoService;


    public CarrinhoItemController(CarrinhoItemActivity activity) {
        this.activity = activity;
        initializeInstances();
        configSpinner();
    }

    private void initializeInstances() {
        produtoDao = new ProdutoDao(this.activity);
        carrinhoDao = new CarrinhoDao(this.activity);
        carrinhoService = new CarrinhoService();
    }


    private void configSpinner() {
        try {
            adapterProdutos = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_spinner_item,
                    produtoDao.buscarTodos(true)
            );
            activity.getSpinnerProduto().setAdapter(adapterProdutos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionar() {
        try {
            MainController.carrinho = carrinhoService.adicionarItemNaComanda(MainController.carrinho, (Produto) activity.getSpinnerProduto().getSelectedItem(), activity.getNpQuantidade().getValue());
            carrinhoDao.getDao().update(MainController.carrinho);
            Toast.makeText(activity, R.string.comanda_item_adicionado_sucesso, Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
