package com.wishlistmongoDB.wishlistmongoDB.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document
public class Wishlist {

    @Id
    private String id;
    private Cliente cliente;
    private List<Produto> produto = new ArrayList<>();

    public boolean deletarProduto(Produto produtos){
        if(existeProduto(produtos)){
            produto.remove(produtos);
            return true;
        }
        return false;
    }

    public boolean existeProduto(Produto produtos){
        return produto.contains(produtos);
    }

    //Getter and Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
}

