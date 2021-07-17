package com.wishlistmongoDB.wishlistmongoDB.service;

import com.wishlistmongoDB.wishlistmongoDB.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save((Produto) produto);
    }

    public Produto buscarProduto(String id) {
        return produtoRepository.findByID(id);
    }

    public Produto atualizaProduto(Produto produto) {
        return produtoRepository.save((Produto) produto);
    }

    public long quantidadeDeProdutos(){
        return produtoRepository.count();
    }

    public Produto findByNome(String  nome) {
        return produtoRepository.findByNome(nome);
    }
}
