package com.study;

import lombok.SneakyThrows;

import java.io.*;
import java.util.Objects;

public class FileManager {

    @SneakyThrows
    public static void main(String[] args) {
        String from = "TempDir#0";
//        String to = "TempDir#33";

        FileManager.deleteFiles(from);
    }

    //// возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) {
        int count = 0;
        File file = new File(path);
        if (file.isFile()) {
            return 1;
        }
        if (file.isDirectory()) {
            for (String naming : Objects.requireNonNull(file.list())) {
                String innerPath = path + File.separator + naming;
                count += countFiles(innerPath);
            }
        }
        return count;
    }

    //// возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        int count = 0;
        File file = new File(path);
        if (file.isDirectory()) {
            count++;
            for (String naming : Objects.requireNonNull(file.list())) {
                String innerPath = path + File.separator + naming;
                if (new File(path).isDirectory()) {
                    count += countDirs(innerPath);
                }
            }
        }
        return count;
    }

    @SneakyThrows
    public static void copy(String from, String to) {
        File fromFile = new File(from);
        File toFile = new File(to);
        if (fromFile.isDirectory() && !toFile.exists()) {
            toFile.mkdirs();
        }
        if (fromFile.isFile()) {
            if (toFile.getParent() != null && !new File(toFile.getParent()).exists()) {
                new File(toFile.getParent()).mkdirs();
            }
            copyFile(fromFile, toFile);
        } else {
            for (String naming : fromFile.list()) {
                String innerPathFrom = from + File.separator + naming;
                String innerPathTo = to + File.separator + naming;
                copy(innerPathFrom, innerPathTo);
            }
        }
    }

    public static void move(String from, String to) {
        copy(from, to);
        deleteFiles(from);
    }

    static void deleteFiles(String from) {
        File file = new File(from);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            for (String naming : file.list()) {
                String innerPathFrom = from + File.separator + naming;
                deleteFiles(innerPathFrom);
                file.delete();
            }
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest)) {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
