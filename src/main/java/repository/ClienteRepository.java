package repository;

import com.wishlistmongoDB.wishlistmongoDB.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

   Cliente findById(String id) ;

    Cliente save(Cliente cliente);


}
