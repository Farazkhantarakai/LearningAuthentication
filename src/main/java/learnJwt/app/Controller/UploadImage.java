package learnJwt.app.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


@RestController
public class UploadImage {

  String directory = "src/main/resources/static/images";
  @PostMapping("/uploadImage")
  public ResponseEntity<String> uploader(@RequestParam("image") MultipartFile image) {




    try {
      //this will set path inthe  local directory
      Path path = Path.of(directory);
      if (!Files.exists(path)) {Files.createDirectory(path);}
      Files.copy(image.getInputStream(), path.resolve(Objects.requireNonNull(image.getOriginalFilename())));
      return ResponseEntity.ok("Image uploaded successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
  @PostMapping("/uploadImages")
  public ResponseEntity<String> uploader(@RequestParam("images") List<MultipartFile> images) {
    Map<String, String> map = new HashMap<>();
    try {
//      Path path=Path.of(directory);
      for (MultipartFile file : images) {
        Path path = Path.of(directory + file.getOriginalFilename());
        Files.createDirectory(path); // this will create path if it does not exist
        Path newPath = path.resolve(Objects.requireNonNull(file.getOriginalFilename()));
        Files.copy(file.getInputStream(), newPath);
        map.put(file.getOriginalFilename(), newPath.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
      return ResponseEntity.ok("Image uploaded successfully"+map.values());
  }
}
