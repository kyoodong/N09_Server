package kr.co.m_n09;

import java.io.File;

public class FileManager {

    public static void makeParentDirectory(File file) {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
    }
}
