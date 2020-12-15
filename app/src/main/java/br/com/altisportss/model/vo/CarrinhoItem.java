package br.com.altisportss.model.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.altisportss.util.UtilNumberFormat;

@DatabaseTable
public class CarrinhoItem {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Produto produto;

    @DatabaseField(canBeNull = false)
    private Integer quantidade;

    @DatabaseField(canBeNull = false)
    private Double subtotal;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Carrinho carrinho;

    public CarrinhoItem() {
    }

    public CarrinhoItem(Produto produto, Integer quantidade, Double subtotal, Carrinho carrinho) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
        this.carrinho = carrinho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return produto.getNome() + " - " + UtilNumberFormat.deDecimalParaStringR$(produto.getValor()) + " - " + quantidade + " - " + UtilNumberFormat.deDecimalParaStringR$(subtotal);
    }
}
