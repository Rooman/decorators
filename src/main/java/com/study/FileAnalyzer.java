package com.study;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileAnalyzer {
    private static final int BUFFER_SIZE = 8192; // 8KB
    private static final String UTF = "UTF-8";
    private static final String REGEX = ".?!";

    public static int getOccurrences(String path, String word) throws IOException {
        String content = readContent(path);
        List<String> sentences = split(content);
        List<String> sentencesInOccurrence = filter(sentences, word);

        return countWord(sentencesInOccurrence, word);
    }

    static String readContent(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (InputStream inputStream = new FileInputStream(path);
             BufferedReader bufferedReader = new BufferedReader(
                     new InputStreamReader(inputStream, UTF), BUFFER_SIZE)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    static List<String> split(String content) {
        return Arrays.asList(content.split(REGEX));
    }

    static List<String> filter(List<String> sentences, String word) {
        return null;
    }

    static int countWord(List<String> sentencesInOccurrence, String word) {
        return 0;
    }

}
