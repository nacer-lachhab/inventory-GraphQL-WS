package ma.nacer.inventoryservice.repositories;

import ma.nacer.inventoryservice.entities.Category;
import ma.nacer.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Set<Category> findCategoryByLibelle(String libelle);
}
