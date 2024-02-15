package hei.school.sary.endpoint.rest.sary.controller;

import hei.school.sary.dto.ImageLink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    @GetMapping("/{imageId}")
    public ImageLink getImage(@PathVariable String imageId) {
        return null;
    }

    @PutMapping(value = "/bw/{imageId}",
            consumes = {
                    MediaType.IMAGE_PNG_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_GIF_VALUE
            },
            produces = {
                    MediaType.IMAGE_PNG_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_GIF_VALUE
            }
    )
    public MultipartFile blackAndWhite(
            @PathVariable String imageId,
            MultipartFile file
    ) throws IOException {
        return file;
    }

}
