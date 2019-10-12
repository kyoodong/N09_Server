package kr.co.m_n09;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileManager {

    private static void makeParentDirectory(File file) {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
    }

    public static String saveImage(MultipartFile image, String directoryPath) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String fileName = UUID.randomUUID().toString() + format.format(new Date(System.currentTimeMillis())) +
                    image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
            File imageFile = new File(new File(directoryPath), fileName);
            makeParentDirectory(imageFile);
            if (!imageFile.createNewFile())
                return null;

            FileCopyUtils.copy(image.getBytes(), imageFile);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
