package ma.nacer.inventoryservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter @ToString @Builder
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private Set<Product> products;
}
