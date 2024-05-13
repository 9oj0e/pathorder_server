package shop.project.pathorderserver._core;

import lombok.Getter;

@Getter
public enum DefaultFile {
    AVATAR("default/avatar.png"),
    STORE("default/store.jpeg"),
    BEVERAGE("default/beverage.png");

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
