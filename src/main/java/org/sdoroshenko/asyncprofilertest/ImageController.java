package org.sdoroshenko.asyncprofilertest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImageController {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping(value = "/images/{id}")
    public byte[] getImage(@PathVariable("id") String id) throws IOException {
        String fileName = "img" + File.separator + id + ".png";
        Resource resource = resourceLoader.getResource("classpath:" + fileName);
        InputStream inputStream = resource.getInputStream();

        try {
            return FileCopyUtils.copyToByteArray(inputStream);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

        return new byte[0];
    }

}
