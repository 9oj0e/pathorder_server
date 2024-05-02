package shop.project.pathorderserver.store;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SessionStore {
    private int id;
    private String username;
    private String ownerName;
    private String name;

    public SessionStore(Store store) {
        this.id = store.getId();
        this.username = store.getUsername();
        this.ownerName = store.getOwnerName();
        this.name = store.getName();
    }
}
