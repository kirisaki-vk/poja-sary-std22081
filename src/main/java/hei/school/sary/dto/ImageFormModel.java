package hei.school.sary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Getter
@Setter
public class ImageFormModel {
  private MultipartFile image;
}
