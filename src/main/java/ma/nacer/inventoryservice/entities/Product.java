package ma.nacer.inventoryservice.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "code", unique = true,length = 36)
    private String id;
    private  String name;
    private  double price;
    private  int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
