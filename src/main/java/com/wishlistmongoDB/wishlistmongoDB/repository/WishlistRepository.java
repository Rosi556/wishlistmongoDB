package com.wishlistmongoDB.wishlistmongoDB.repository;

import com.wishlistmongoDB.wishlistmongoDB.entity.Cliente;
import com.wishlistmongoDB.wishlistmongoDB.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    Wishlist findByClienteId(String id);

    List<Wishlist> findByCliente(Cliente existeCliente);
}
