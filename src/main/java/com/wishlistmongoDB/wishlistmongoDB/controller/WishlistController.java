package com.wishlistmongoDB.wishlistmongoDB.controller;

import com.wishlistmongoDB.wishlistmongoDB.entity.Cliente;
import com.wishlistmongoDB.wishlistmongoDB.entity.Produto;
import com.wishlistmongoDB.wishlistmongoDB.entity.Wishlist;
import com.wishlistmongoDB.wishlistmongoDB.service.ClienteService;
import com.wishlistmongoDB.wishlistmongoDB.service.ProdutoService;
import com.wishlistmongoDB.wishlistmongoDB.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WishlistController {
    private static final int MAXSIZE = 20;

    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    //Adicionar um produto na wishlist

    @PutMapping("/wishlist/{id_cliente}/{id_produto}")
    public ResponseEntity<Wishlist> adicionarProdutosNaWishlist(@PathVariable String id_cliente, @PathVariable String id_produto) {

        try {
            //ver se o cliente existe
            Cliente cliente = clienteService.buscarCliente(id_cliente);

            if (cliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                //ver se o cliente tem wishlist
                Wishlist wishlistDoCliente = wishlistService.procurarPeloIdCliente(id_cliente);

                Produto produtoAdicionado  = produtoService.buscarProduto(id_produto);

                //se não existir wishlist
                if (wishlistDoCliente == null && produtoAdicionado != null)
                {
                    Wishlist wishlistCriada = new Wishlist();
                    wishlistCriada.setCliente(cliente);

                    List<Produto> produto = new ArrayList<>();
                    produto.add(produtoAdicionado);

                    wishlistCriada.setProduto(produto);
                    wishlistService.criarWishlist(wishlistCriada);

                    return new ResponseEntity<>(HttpStatus.OK);
                }

                //pegar os produtos da wishlist
                List<Produto> listaDeProdutoDoCliente = wishlistDoCliente.getProduto();

                if (produtoAdicionado == null)
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

                //verifica se produto já está na wishlist
                for (int i = 0; i < listaDeProdutoDoCliente.size(); i++) {
                    if (listaDeProdutoDoCliente.get(i) == produtoAdicionado)
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                //verifica se já existem 20 produtos na wishlist
                if(listaDeProdutoDoCliente.size() >= 20)
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

                //adiciona o produto na lista do cliente
                listaDeProdutoDoCliente.add(produtoAdicionado);

                //atualiza a lista do cliente junto com o novo produto
                wishlistDoCliente.setProduto(listaDeProdutoDoCliente);
                wishlistService.atualizarWishlist(wishlistDoCliente);

                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar todas as wishLists cadastradas

    @GetMapping("/wishlist")
    public List<Wishlist> visualizarWishlist(){
        Iterable<Wishlist> iterable = wishlistService.visualizarWishlist();
        List<Wishlist> wishlists = new ArrayList<>();
        iterable.forEach(wishlists::add);
        return wishlists;
    }

    //Visualizar wishlist pelo Id do cliente

    @GetMapping("/wishlist/cliente/{id_cliente}")
    public ResponseEntity<Wishlist> visualizarWishListIdCliente(@PathVariable String id_cliente){
        try {
            Cliente existeCliente = clienteService.findById(id_cliente);
            Wishlist wishlist = wishlistService.findByClientId(id_cliente);

            if (existeCliente == null || wishlist == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(wishlist, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deletar um produto na WishList

    @DeleteMapping("/wishlist/cliente/{id_cliente}/produto/{id_produto}")
    public ResponseEntity<Wishlist> removerProdutoNaWishlist(@PathVariable String id_produto, @PathVariable String id_cliente) {
        try {
            Cliente existeCliente = clienteService.findById(id_cliente);
            Wishlist wishlist = wishlistService.findByClientId(id_cliente);
            Produto existeProduto = produtoService.buscarProduto(id_produto);

            if (existeCliente == null || wishlist == null || existeProduto == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            if (!wishlist.deletarProduto(existeProduto))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            wishlist = wishlistService.criarWishlist(wishlist);
            return new ResponseEntity<>(wishlist, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Consultar se um determinado produto está na wishlist do cliente

    @GetMapping("/wishlist/cliente/{id_cliente}/produto/{nome}")
    public ResponseEntity<Wishlist> buscarProdutoNaWishlistCliente(@PathVariable String id_cliente,@PathVariable(value = "nome") String nome) {
        try {
            Cliente existeCliente = clienteService.findById(id_cliente);
            Wishlist wishlist = wishlistService.findByClientId(id_cliente);
            Produto produto = produtoService.findByNome(nome);

            if (existeCliente == null || wishlist == null || produto == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
