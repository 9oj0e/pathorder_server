package shop.project.pathorderserver._core;

import lombok.Getter;

@Getter
public enum MimeType {
    JPEG("image/jpeg", ".jpg"),
    PNG("image/png", ".png");

    private final String mimeType;
    private final String extension;

    MimeType(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
    }

    // MIME 타입 문자열로부터 해당 Enum 상수를 찾는 메소드
    public static MimeType findByMimeType(String mimeType) {
        for (MimeType type : values()) {
            if (type.getMimeType().equals(mimeType)) {
                return type;
            }
        }
        return JPEG; // 기본값
    }
}
