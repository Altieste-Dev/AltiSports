package br.com.altisportss.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

@DatabaseTable
public class Carrinho {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private Double valorTotal;

    @ForeignCollectionField(eager = true)
    private Collection<CarrinhoItem> items;


    public Carrinho() {
    }

    public Carrinho(Integer id, Double valorTotal, List<CarrinhoItem> items) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.items = items;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<CarrinhoItem> getItems() {
        return items;
    }

    public void setItems(Collection<CarrinhoItem> items) {
        this.items = items;
    }
}
