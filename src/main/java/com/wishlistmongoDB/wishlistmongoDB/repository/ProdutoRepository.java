package com.wishlistmongoDB.wishlistmongoDB.repository;

import com.wishlistmongoDB.wishlistmongoDB.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    Produto findByNome(String nome);
}
