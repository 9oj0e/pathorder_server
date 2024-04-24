package shop.project.pathorderserver.store;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "store_tb")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
