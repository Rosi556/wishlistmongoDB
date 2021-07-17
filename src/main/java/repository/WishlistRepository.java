package repository;

import com.wishlistmongoDB.wishlistmongoDB.entity.Cliente;
import com.wishlistmongoDB.wishlistmongoDB.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    static Wishlist findByClienteID(String id) {
        return null;
    }

    List<Wishlist> findByCliente(Cliente existeCliente);
}
