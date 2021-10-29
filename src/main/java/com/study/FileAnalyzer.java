package com.study;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileAnalyzer {
    private static final int BUFFER_SIZE = 8192; // 8KB
    private static final String UTF = "UTF-8";
    private static final String REGEX = "((?<=\\.)|(?<=\\?)|(?<=!))";

    public static int countMatches(String path, String word) throws IOException {
        String content = readContent(path);
        List<String> sentences = split(content);
        List<String> sentencesInOccurrence = filter(sentences, word);

        return countWord(sentencesInOccurrence, word);
    }

    static String readContent(String path) throws IOException {
        String content;

        try (InputStream inputStream = new FileInputStream(path);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF), BUFFER_SIZE)) {

            content = bufferedReader.lines().toString();
        }
        return content;
    }

    static List<String> split(String content) {
        return Arrays.stream(content.split(REGEX))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    static List<String> filter(List<String> sentences, String word) {
        return null;
    }

    static int countWord(List<String> sentencesInOccurrence, String word) {
        return 0;
    }

}
