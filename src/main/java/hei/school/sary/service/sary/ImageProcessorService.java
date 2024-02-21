package hei.school.sary.service.sary;

import hei.school.sary.file.BucketComponent;
import hei.school.sary.file.FileHash;
import hei.school.sary.repository.ImageProcessorRepository;
import hei.school.sary.repository.model.ImageProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageProcessorService {
    private final FileService fileService;
    private final ImageProcessorRepository imageProcessorRepo;
    private final BucketComponent bucketComponent;

    public List<FileHash> applyBW(MultipartFile file, String key) throws IOException {
        List<FileHash> fileHashes = new ArrayList<>();
        String fileSuffix =
                Objects.requireNonNullElse(file.getContentType(), "application/tmp").split("/")[0];
        String originalFileKey = String.format("og_%s", key);
        String processedFileKey = String.format("pc_%s_bw", key);
        File originalImage = File.createTempFile(originalFileKey, "." + fileSuffix);
        File processedImage =
                fileService.convert(
                        convertBufferType(ImageIO.read(file.getInputStream()), BufferedImage.TYPE_BYTE_GRAY),
                        Objects.requireNonNullElse(file.getContentType(), "unsupported"),
                        String.format("%s.%s", processedFileKey, fileSuffix)
                );
        ImageIO.write(ImageIO.read(file.getInputStream()), fileSuffix, originalImage);
        saveImages(key, "bw");
        fileHashes.add(bucketComponent.upload(originalImage, originalFileKey));
        fileHashes.add(bucketComponent.upload(processedImage, processedFileKey));
        return fileHashes;
    }

    private BufferedImage convertBufferType(BufferedImage image, int imageType) throws IOException {
        BufferedImage convertedImage =
                new BufferedImage(image.getWidth(), image.getHeight(), imageType);
        Graphics imageGraphics = convertedImage.getGraphics();
        imageGraphics.drawImage(image, 0, 0, null);
        imageGraphics.dispose();
        return convertedImage;
    }

    private void saveImages(String key, String operation) {
        String originalFileKey = String.format("og_%s", key);
        String processedFileKey = String.format("pc_%s_%s", key, operation);
        imageProcessorRepo.save(
                ImageProcess.builder()
                        .id(key)
                        .original(originalFileKey)
                        .edited(processedFileKey)
                        .time(Timestamp.from(Instant.now()))
                        .operation(operation)
                        .build()
        );
    }
}
