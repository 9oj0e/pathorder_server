package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
