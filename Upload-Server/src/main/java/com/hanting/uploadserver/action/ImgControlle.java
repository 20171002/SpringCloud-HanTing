package com.hanting.uploadserver.action;

import com.hanting.uploadserver.file.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ImgControlle {
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImgControlle(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping(value = "img/**", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> show(HttpServletRequest request) {
        String img = request.getRequestURL().toString().split("/api/img/")[1];
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Upload.UPLOADED_FOLDER + img));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
