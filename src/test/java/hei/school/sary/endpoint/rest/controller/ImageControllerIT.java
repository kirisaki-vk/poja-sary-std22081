package hei.school.sary.endpoint.rest.controller;

import hei.school.sary.conf.FacadeIT;
import hei.school.sary.dto.ImageFormModel;
import hei.school.sary.endpoint.rest.controller.sary.ImageController;
import java.io.IOException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ImageControllerIT extends FacadeIT {
  @Autowired ImageController controller;

  @Test
  public void can_get_image() {
    controller.getImage("id");
  }

  @Test
  public void cen_create_image() throws IOException {
    controller.blackAndWhite("id", new ImageFormModel());
  }
}
