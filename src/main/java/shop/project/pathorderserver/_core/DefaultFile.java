package shop.project.pathorderserver._core;

import lombok.Getter;

@Getter
public enum DefaultFile {
    AVATAR("default/avatar.png"),
    USER2("default/user2.jpg"),
    USER5("default/user5.jpg"),
    STORE("default/cafe.png"),
    STORE1("default/cafe1.png"),
    STORE2("default/cafe2.png"),
    STORE3("default/cafe3.png"),
    STORE4("default/cafe4.png"),
    STORE5("default/cafe5.png"),
    STORE6("default/cafe6.png"),
    AMERICANO("default/americano.png"),
    LATTE("default/latte.png"),
    MACCHIATO("default/macchiato.png"),
    MOCHA("default/mocha.png"),
    COFFEE("default/coffee.jpeg"),
    COFFEE1("default/itte1.jpg"),
    COFFEE2("default/itte2.jpg"),
    COFFEE3("default/itte3.jpg"),
    COFFEE4("default/itte4.jpg"),
    COFFEE5("default/itte5.jpg"),
    COFFEE6("default/itte6.jpg"),
    COFFEE7("default/itte7.jpg"),
    COFFEE8("default/itte8.jpg"),
    COFFEE9("default/itte9.jpg"),
    COFFEE10("default/itte10.jpg"),
    REVIEW1("default/itte10.jpg"),
    REVIEW2("default/itte10.jpg");

    private final String path;

    DefaultFile(String path) {
        this.path = path;
    }

    public static boolean contains(String path) {
        for (DefaultFile defaultFile : values()) {
            if (defaultFile.getPath().equals(path)) {
                return true;
            }
        }
        return false;
    }
}
