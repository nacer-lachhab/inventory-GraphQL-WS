package ma.nacer.inventoryservice;

import ma.nacer.inventoryservice.entities.Category;
import ma.nacer.inventoryservice.entities.Product;
import ma.nacer.inventoryservice.repositories.CategoryRepository;
import ma.nacer.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            CategoryRepository categoryRepository,
            ProductRepository productRepository){
        return args -> {
            List.of("computer","smartPhone","TV").forEach(
                    cat-> categoryRepository.save(
                                                Category.builder()
                                                        .libelle(cat)
                                                        .build())//fin save
            );//fin foreach
            categoryRepository.findAll().forEach(cat -> {
                for (int i = 0; i < 5; i++) {
                    Product product =
                            Product.builder()
                                    .name("Article"+i)
                                    .price((1+new Random().nextInt(9))*Math.pow(10,i+1))
                                    .quantity(1+new Random().nextInt(9))
                                    .category(cat)
                                    .build();
                    productRepository.save(product);
                }
            });
            System.out.println("App Started with success...");
        };
    }
}
