package shop.project.pathorderserver.menu;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "menu_tb")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
