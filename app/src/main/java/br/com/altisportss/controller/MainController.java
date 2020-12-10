package br.com.altisportss.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.altisportss.R;
import br.com.altisportss.dao.CarrinhoDao;
import br.com.altisportss.dao.CarrinhoItemDao;
import br.com.altisportss.model.Carrinho;
import br.com.altisportss.model.CarrinhoItem;
import br.com.altisportss.service.CarrinhoItemService;
import br.com.altisportss.service.CarrinhoService;
import br.com.altisportss.util.UtilNumberFormat;
import br.com.altisportss.view.CarrinhoItemActivity;
import br.com.altisportss.view.JogadoresActivity;
import br.com.altisportss.view.MainActivity;
import br.com.altisportss.view.ProdutoActivity;

public class MainController {

    private MainActivity activity;

    private CarrinhoDao carrinhoDao;
    private CarrinhoItemDao carrinhoItemDao;
    private CarrinhoService carrinhoService;
    private CarrinhoItemService carrinhoItemService;

    public static Carrinho carrinho;

    private CarrinhoItem carrinhoItem;


    //Para o ListView
    private ArrayAdapter<CarrinhoItem> adapterCarrinhoItems;
    private List<CarrinhoItem> listCarrinhoItems;

    public MainController(MainActivity mainActivity) {
        this.activity = mainActivity;
        carrinhoDao = new CarrinhoDao(activity);
        carrinhoItemDao = new CarrinhoItemDao(activity);
        carrinhoItemService = new CarrinhoItemService();
        inicializarCarrinho();
        configListViewCarrinhoItems();
    }

    private void inicializarCarrinho() {
        try {
            carrinhoService = new CarrinhoService();
            carrinho = carrinhoService.iniciarComanda();
            carrinho = carrinhoDao.getDao().createIfNotExists(carrinho);
            carrinho = carrinhoDao.getDao().queryForId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void redirecionarParaContextoDeAdicionarProduto() {
        Intent intent = new Intent(activity, CarrinhoItemActivity.class);
        activity.startActivity(intent);
    }

    public void redirecionarParaContextoDeGerenciarProdutos() {
        Intent intent = new Intent(activity, ProdutoActivity.class);
        activity.startActivity(intent);
    }

    private void configListViewCarrinhoItems() {
        try {
            listCarrinhoItems = new ArrayList<CarrinhoItem>(carrinhoDao.getDao().queryForId(1).getItems());
            adapterCarrinhoItems = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    listCarrinhoItems
            );
            activity.getLvCarrinhoItems().setAdapter(adapterCarrinhoItems);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addShortClickListener();
        addLongClickListener();
    }

    private void addLongClickListener() {
        activity.getLvCarrinhoItems().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                carrinhoItem = adapterCarrinhoItems.getItem(position);
                confirmarExclusaoAction(carrinhoItem);
                return true;
            }
        });

    }

    private void addShortClickListener() {
        activity.getLvCarrinhoItems().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                carrinhoItem = adapterCarrinhoItems.getItem(position);
                editarComandaItemDialog(carrinhoItem);
            }
        });

    }

    public void confirmarExclusaoAction(final CarrinhoItem item) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle(R.string.excluindo_item_da_comanda);
        alerta.setMessage(activity.getString(R.string.deseja_realmente_excluir_item) + item.getProduto().getNome() + activity.getString(R.string.da_comanda));
        alerta.setIcon(android.R.drawable.ic_menu_delete);
        alerta.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                carrinhoItem = null;
            }
        });
        alerta.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    carrinhoItemDao.getDao().delete(item);
                    adapterCarrinhoItems.remove(item);
                    atualizarValorTotalDoCarrinho();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                carrinhoItem = null;
            }
        });
        alerta.show();
    }

    public void editarComandaItemDialog(final CarrinhoItem item) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle(activity.getString(R.string.editar_quantidade_do_item) + item.getProduto().getNome());
        alerta.setIcon(android.R.drawable.ic_menu_edit);
        alerta.setPositiveButton(R.string.maisUm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adicionarMaisUmaQuantidade(item);
                atualizarEPersistirValoresDaComanda();
                Toast.makeText(activity, R.string.somado_mais_um_a_quantidade_do_item_selecionado, Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton(R.string.menosUm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                subtrairMenosUmaQuantidade(item);
                atualizarEPersistirValoresDaComanda();
                Toast.makeText(activity, R.string.subtraido_menos_um_a_quantidade_do_item_selecionado, Toast.LENGTH_SHORT).show();

            }
        });
        alerta.setNeutralButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                carrinhoItem = null;
            }
        });
        alerta.show();
    }

    public void limparLista() {
        try {
            for (CarrinhoItem item : carrinho.getItems()) {
                carrinhoItemDao.getDao().delete(item);
            }
            adapterCarrinhoItems.clear();
            List<CarrinhoItem> items = new ArrayList<>(carrinhoDao.getDao().queryForId(carrinho.getId()).getItems());
            adapterCarrinhoItems.addAll(items);
            adapterCarrinhoItems.notifyDataSetChanged();
            atualizarValorTotalDoCarrinho();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void atualizarEPersistirValoresDaComanda() {
        try {
            carrinho = carrinhoService.recalcularTotal(carrinho);
            carrinhoDao.getDao().update(carrinho);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarMaisUmaQuantidade(CarrinhoItem item) {
        CarrinhoItem carrinhoItem = carrinhoItemService.adicionarMaisUm(item);
        try {
            carrinhoItemDao.getDao().update(carrinhoItem);
            atualizarItemsDoCarrinho();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void subtrairMenosUmaQuantidade(CarrinhoItem item) {
        verificaSeQuantidadeEhIgualAZeroEDeletaDaLista(item);

        CarrinhoItem carrinhoItem = carrinhoItemService.subtrairMenosUm(item);
        try {
            carrinhoItemDao.getDao().update(carrinhoItem);
            atualizarItemsDoCarrinho();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verificaSeQuantidadeEhIgualAZeroEDeletaDaLista(CarrinhoItem item) {
        if (item.getQuantidade() == 0) {
            try {
                carrinhoItemDao.getDao().delete(item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        atualizarEPersistirValoresDaComanda();
    }

    public void atualizarItemsDoCarrinho() {

        try {
            verificaSeAlistaContemUmItemComQuantidadeZeradaERemoveOItem(carrinhoDao.getDao().queryForId(carrinho.getId()));
            adapterCarrinhoItems.clear();
            List<CarrinhoItem> items = new ArrayList<>(carrinhoDao.getDao().queryForId(carrinho.getId()).getItems());
            adapterCarrinhoItems.addAll(items);
            adapterCarrinhoItems.notifyDataSetChanged();
            atualizarValorTotalDoCarrinho();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarValorTotalDoCarrinho() throws SQLException {
        carrinho = carrinhoDao.getDao().queryForId(carrinho.getId());
        carrinho = carrinhoService.recalcularTotal(carrinho);
        carrinhoDao.getDao().update(carrinho);
        activity.getTvTotal().setText(UtilNumberFormat.deDecimalParaStringR$(carrinho.getValorTotal()));
    }

    public void verificaSeAlistaContemUmItemComQuantidadeZeradaERemoveOItem(Carrinho carrinho) {
        for (CarrinhoItem item : carrinho.getItems()) {
            if (item.getQuantidade() == 0 || item.getQuantidade() < 0) {
                verificaSeQuantidadeEhIgualAZeroEDeletaDaLista(item);
            }
        }
    }

    public void redirecionarParaJogadores() {
        Intent intent = new Intent(activity, JogadoresActivity.class);
        activity.startActivity(intent);
    }
}
