package shop.project.pathorderserver.transaction;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction_tb d")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
