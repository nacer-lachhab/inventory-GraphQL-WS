package ma.nacer.inventoryservice.web;

import ma.nacer.inventoryservice.dto.ProductRequest;
import ma.nacer.inventoryservice.entities.Category;
import ma.nacer.inventoryservice.entities.Product;
import ma.nacer.inventoryservice.repositories.CategoryRepository;
import ma.nacer.inventoryservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class ProductGraphQLController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @QueryMapping
    public List<Product> productsList() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Set<Product> productByName(@Argument String name) {
        Set<Product> result = productRepository
                .findProductByName(name);
//        result.forEach(e->System.out.println(e.toString()));
        if (result.isEmpty())
            throw new RuntimeException("No Product Found with name: " + name);
        return result;
    }

    @MutationMapping
    public Product saveProduct(@Argument ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .quantity(productRequest.quantity())
                .build();
        Category resultCategoryById = categoryRepository
                .findById(productRequest.idCategory())
                .orElseThrow(
                        () -> new RuntimeException(
                                String.format("Erreur, Categorie avec id: %d inexistante!!!", productRequest.idCategory())));
        product.setCategory(resultCategoryById);
        return productRepository.save(product);
    }

    @MutationMapping
    public Product editProduct(
            @Argument String idProduct,
            @Argument ProductRequest productRequest) {
        Product productToEdit = productRepository
                .findById(idProduct)
                .orElseThrow(() -> new RuntimeException(String.format("Erreur, l'id: %s ne correspond a aucun Produit", idProduct)));
        productToEdit.setCategory(
                categoryRepository.findById(
                                productRequest
                                        .idCategory())
                        .get());
        productToEdit.setPrice(productRequest.price());
        productToEdit.setQuantity(productRequest.quantity());
        productToEdit.setName(productRequest.name());
        return productRepository.save(productToEdit);
    }

    @MutationMapping
    public void deleteProduct(@Argument String idProduct) {
        Product productTodelete =
                productRepository
                        .findById(idProduct)
                        .orElseThrow(() -> new RuntimeException(String.format("Erreur, l'id: %s ne correspond a aucun Produit", idProduct)));
        productRepository.delete(productTodelete);
    }
}
