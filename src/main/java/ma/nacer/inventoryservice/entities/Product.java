package ma.nacer.inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
//@ToString
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
//    @JsonBackReference
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
