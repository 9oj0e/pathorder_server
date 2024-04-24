package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "option_tb")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
