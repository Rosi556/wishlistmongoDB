package com.wishlistmongoDB.wishlistmongoDB.repository;

import com.wishlistmongoDB.wishlistmongoDB.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {



}
