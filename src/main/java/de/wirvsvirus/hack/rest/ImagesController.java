package de.wirvsvirus.hack.rest;

import com.google.common.base.Preconditions;
import de.wirvsvirus.hack.spring.UserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * note: requires images folder to exist, must be relative to current folder
 */
@RestController
@RequestMapping("/images")
@Slf4j
public class ImagesController {

    @GetMapping(value = "/avatar/{userId}")
    public void getImageAsByteArray(HttpServletResponse response,
                            @PathVariable("userId") UUID userId) throws IOException {

        final Path imageDir = Paths.get("../stimmungsringe-images");
        Preconditions.checkState(Files.isDirectory(imageDir), "Image directory <%s> missing", imageDir);

        final Path avatarFile = imageDir.resolve("avatar-" + userId + ".jpg");

        if (!Files.isRegularFile(avatarFile)) {
            log.warn("No avatar file found <{}> - serving fallback image", avatarFile);
            final InputStream fallbackAvatar = ImagesController.class.getResourceAsStream("/images/avatar/avatar-fallback.jpg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(fallbackAvatar, response.getOutputStream());
            return;
        }
        Preconditions.checkState(Files.isRegularFile(avatarFile), "Avatar image file missing: %s", avatarFile);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        Files.copy(avatarFile, response.getOutputStream());
    }

}
