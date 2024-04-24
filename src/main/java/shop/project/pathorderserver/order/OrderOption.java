package shop.project.pathorderserver.order;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_option_tb")
public class OrderOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
