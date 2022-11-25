package ma.nacer.inventoryservice.repositories;

import ma.nacer.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Set<Product> findProductByName(String name);
}
