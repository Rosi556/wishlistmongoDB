package com.wishlistmongoDB.wishlistmongoDB.service;

import com.wishlistmongoDB.wishlistmongoDB.entity.Cliente;
import com.wishlistmongoDB.wishlistmongoDB.entity.Produto;
import com.wishlistmongoDB.wishlistmongoDB.entity.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wishlistmongoDB.wishlistmongoDB.repository.WishlistRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ClienteService clienteService;

    private List<Produto> produto = new ArrayList<>();

    //Criar uma wishlist
    public Wishlist criarWishlist(Wishlist wishlist){
        return wishlistRepository.save(wishlist);
    }

    //Atualizar wishlist
    public Wishlist atualizarWishlist(Wishlist wishlist){
        return wishlistRepository.save(wishlist);
    }

    //Visualizar todos os produtos na WishList
    public List<Wishlist> visualizarWishlist(){
        Iterable<Wishlist> iterable = wishlistRepository.findAll();
        List<Wishlist> wishlists = new ArrayList<>();
        iterable.forEach(wishlists::add);
        return wishlists;
    }

    //Metodo para saber se o cliente existe
    public Wishlist findByClientId(String id) {
        Cliente existeCliente = clienteService.findById(id);
        if (existeCliente != null) {
            List<Wishlist> listWishlist = wishlistRepository.findByCliente(existeCliente);
            if (!listWishlist.isEmpty()) {
                return listWishlist.get(0);
            }
        }
        return null;
    }

    //Metodo para saber se o cliente existe
    public Wishlist procurarPeloIdCliente(String id) {
        return wishlistRepository.findByClienteId(id);

    }
}
