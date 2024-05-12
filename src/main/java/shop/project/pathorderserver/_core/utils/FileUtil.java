package shop.project.pathorderserver._core.utils;

import org.springframework.web.multipart.MultipartFile;
import shop.project.pathorderserver._core.errors.exception.Exception400;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class FileUtil {

    public static String fileUpload(MultipartFile file) {
        // UUID_파일 이름.확장자
        String newFilename = UUID.randomUUID()  + "_" + file.getOriginalFilename();
        Path newFilePath = Paths.get("./upload/" + newFilename);
        try {
            // boolean isEmpty = file == null || file.isEmpty();
            Files.write(newFilePath, file.getBytes());
        } catch (IOException e) {
            throw new Exception400("잘못된 요청입니다.");
        }
        return newFilename; // 생성된 파일 이름 return
    }

    public static String base64Upload(String encodedData, String filename, String extension) {
        // UUID_파일 이름.확장자
        String newFilename = UUID.randomUUID() + "_" + filename + "." + extension;
        Path newFilePath = Paths.get("./upload/" + newFilename);
        try {
            byte[] decodedByte = Base64.getDecoder().decode(encodedData);
            Files.write(newFilePath, decodedByte);
        } catch (IOException e) {
            throw new Exception400("잘못된 요청입니다.");
        }
        return newFilename; // 생성된 파일 이름 return
    }

    public static String getFilePath(String filename) {
        return "/upload/" + filename; // 파일 경로 return
    }
}
