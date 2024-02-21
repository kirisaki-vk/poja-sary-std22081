package hei.school.sary.service.sary;

import hei.school.sary.file.BucketComponent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
  private final BucketComponent bucketComponent;

  public File convert(BufferedImage image, String imageType, String fileName) throws IOException {
    File fileOutput = File.createTempFile(fileName, null);
    if (imageType.equals(MediaType.IMAGE_JPEG_VALUE)) {
      ImageIO.write(image, "jpg", fileOutput);
    } else if (imageType.equals(MediaType.IMAGE_PNG_VALUE)) {
      ImageIO.write(image, "png", fileOutput);
    } else {
      throw new RuntimeException("File format not supported");
    }
    return fileOutput;
  }
}
