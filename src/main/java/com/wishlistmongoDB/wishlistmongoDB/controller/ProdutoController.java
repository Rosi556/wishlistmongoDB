package com.wishlistmongoDB.wishlistmongoDB.controller;

import com.wishlistmongoDB.wishlistmongoDB.entity.Produto;
import com.wishlistmongoDB.wishlistmongoDB.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //Adicionar Produto

    @PostMapping("/produto")
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        try {
            Produto respostaProduto = produtoService.adicionarProduto(produto);
            return new ResponseEntity<>(produtoService.adicionarProduto(produto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar produto pelo ID
    @GetMapping("/produto/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable String id) {
        try {
            return new ResponseEntity<>(produtoService.buscarProduto(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
