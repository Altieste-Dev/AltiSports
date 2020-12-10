package br.com.altisportss.dao.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import br.com.altisportss.model.Carrinho;
import br.com.altisportss.model.CarrinhoItem;
import br.com.altisportss.model.Produto;

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "meuCarrinho.db";
    private static final int DATABASE_VERSION = 2;

    public MyORMLiteHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {

            TableUtils.createTableIfNotExists(connectionSource, Produto.class);
            TableUtils.createTableIfNotExists(connectionSource, Carrinho.class);
            TableUtils.createTableIfNotExists(connectionSource, CarrinhoItem.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Produto.class, true);
            TableUtils.dropTable(connectionSource, Carrinho.class, true);
            TableUtils.dropTable(connectionSource, CarrinhoItem.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
