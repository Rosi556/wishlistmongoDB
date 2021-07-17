package repository;

import com.wishlistmongoDB.wishlistmongoDB.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

    Produto findByID(String id);

    Produto findByNome(String nome);
}
