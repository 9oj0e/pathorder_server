package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_menu")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
