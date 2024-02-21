package hei.school.sary.dto;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImagesLinks {
  private URL originalLink;
  private URL processedLink;
}
