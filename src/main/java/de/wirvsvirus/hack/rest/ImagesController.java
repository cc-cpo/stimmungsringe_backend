package de.wirvsvirus.hack.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@Slf4j
public class ImagesController {

    @GetMapping(value = "/avatar/{userId}")
    public void getImageAsByteArray(HttpServletResponse response,
                            @PathVariable("userId") UUID userId) throws IOException {

        final InputStream fallbackAvatar = ImagesController.class.getResourceAsStream("/images/avatar/avatar-fallback.jpg");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(fallbackAvatar, response.getOutputStream());
    }

}
