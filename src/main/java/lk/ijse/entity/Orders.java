package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    private String id;
    private String price;
    private String qty;
   @ManyToOne
    private Customer customer;

}
