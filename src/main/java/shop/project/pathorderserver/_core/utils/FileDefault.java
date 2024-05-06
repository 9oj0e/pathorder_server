package shop.project.pathorderserver._core.utils;

public enum FileDefault {
    AVATAR("default/avatar.png"),
    STORE("default/store.png"),
    BEVERAGE("default/beverage.png");

    private final String path;

    FileDefault(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
