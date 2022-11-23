package ma.nacer.inventoryservice.web;

import ma.nacer.inventoryservice.entities.Product;
import ma.nacer.inventoryservice.repositories.ProductRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {

    private ProductRepository productRepository;

    public ProductGraphQLController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryMapping
    public List<Product> productList(){
        return productRepository.findAll();
    }
}
