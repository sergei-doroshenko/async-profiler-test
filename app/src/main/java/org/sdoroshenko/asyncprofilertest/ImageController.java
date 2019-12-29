package org.sdoroshenko.asyncprofilertest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Arrays;

@RestController
public class ImageController {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());
    final String fileSeparator = System.getProperty("file.separator");

    @GetMapping(value = "/images/{id}")
    public byte[] getImage(@PathVariable("id") String id) throws IOException {
        String userHome = System.getProperty("user.home");
        File theDir = new File(userHome + fileSeparator + "img");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        String fileName = userHome + fileSeparator + "img" + fileSeparator + id + ".png";
        File file = new File(fileName);

        if (file.exists()) {
            return readFromFile(file);
        } else {
            if (file.createNewFile()) {
                byte[] content = readResource();
                writeToFile(file, content);
                return readFromFile(file);
            }

            throw new RuntimeException("ERROR! File: " + file.getName() + " wasn't created!");
        }
    }

    public byte[] readResource() throws IOException {
        String fileName = "/img" + File.separator + "1001.png";
        InputStream is = getClass().getResourceAsStream(fileName);
        return getBytes(is);
    }

    public void writeToFile(File file, byte[] content) throws IOException {
        FileOutputStream os = new FileOutputStream(file);
        os.write(content);
        os.flush();
    }

    public byte[] readFromFile(File file) throws IOException {
        FileInputStream is = new FileInputStream(file);
        return getBytes(is);

    }

    private byte[] getBytes(InputStream is) throws IOException {
        byte[] buf = new byte[8192];
        int len = is.read(buf);
        if (len < buf.length) {
            return Arrays.copyOf(buf, len);
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream(16384);
        while (len != -1) {
            os.write(buf, 0, len);
            len = is.read(buf);
        }
        return os.toByteArray();
    }

}
