package shop.project.pathorderserver._core;

import lombok.Getter;

@Getter
public enum DefaultFile {
    AVATAR("default/avatar.png"),
    STORE("default/store.png"),
    BEVERAGE("default/beverage.png");

    private final String path;

    DefaultFile(String path) {
        this.path = path;
    }
}
