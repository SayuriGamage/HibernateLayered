package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

    @Id
    private String id;
    private String name;
    private String address;
    @OneToMany (mappedBy = "customer")
    private List<Order> orderList;

    public Customer(String id, String name, String address) {
        this.id=id;
        this.name=name;
        this.address=address;
    }
}
