package shop.project.pathorderserver._core;

public enum DefaultFile {
    AVATAR("default/avatar.png"),
    STORE("default/store.png"),
    BEVERAGE("default/beverage.png");

    private final String path;

    DefaultFile(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
