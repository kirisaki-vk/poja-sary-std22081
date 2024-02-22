package hei.school.sary.repository.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image_process")
public class ImageProcess {
  @Id private String id;

  private String original;
  private String edited;
  private String operation;
  private Timestamp time;
}
