package hei.school.sary.endpoint.rest.controller.sary;

import hei.school.sary.dto.ImageFormModel;
import hei.school.sary.dto.ImagesLinks;
import hei.school.sary.file.FileHash;
import hei.school.sary.service.sary.ImageProcessorService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
  private final ImageProcessorService imageProcessor;

  @GetMapping("/{imageId}")
  public ImagesLinks getImage(@PathVariable String imageId) {
    return imageProcessor.getImages(imageId);
  }

  @PutMapping(
      value = "/bw/{imageId}",
      consumes = {
        MediaType.IMAGE_PNG_VALUE,
        MediaType.IMAGE_JPEG_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE,
      })
  public List<FileHash> blackAndWhite(
      @PathVariable String imageId, @ModelAttribute ImageFormModel image) throws IOException {
    return imageProcessor.applyBW(image.getImage(), imageId);
  }
}
