package ma.nacer.inventoryservice.web;

import ma.nacer.inventoryservice.dto.CategoryRequest;
import ma.nacer.inventoryservice.dto.ProductRequest;
import ma.nacer.inventoryservice.entities.Category;
import ma.nacer.inventoryservice.entities.Product;
import ma.nacer.inventoryservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class CategoryGraphQLController {

    @Autowired
    private CategoryRepository categoryRepository;

    @QueryMapping
    public List<Category> categoriesList(){
        return categoryRepository.findAll();
    }

    @QueryMapping
    public Set<Category> categoryByLibelle(@Argument String libelle){
        Set<Category> result = categoryRepository
                .findCategoryByLibelle(libelle);
        if(result.isEmpty())
            throw new RuntimeException("No Category Found with libelle: "+libelle);
        return result;
    }

    @MutationMapping
    public Category saveCategory(@Argument CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .libelle(categoryRequest.libelle())
                .build();
        return categoryRepository.save(category);
    }

    @MutationMapping
    public Category editCategory(
            @Argument long idCategory,
            @Argument CategoryRequest categoryRequest) {
        Category categoryToEdit = categoryRepository
                .findById(idCategory)
                .orElseThrow(() -> new RuntimeException(String.format("Erreur, l'id: %s ne correspond a aucune categorie", idCategory)));
        categoryToEdit.setLibelle(categoryRequest.libelle());
        return categoryRepository.save(categoryToEdit);
    }

    @MutationMapping
    public void deleteCategory(@Argument long idCategory) {
        Category categoryTodelete =
                categoryRepository
                        .findById(idCategory)
                        .orElseThrow(() -> new RuntimeException(String.format("Erreur, l'id: %s ne correspond a aucune categorie", idCategory)));
        categoryRepository.delete(categoryTodelete);
    }
}
