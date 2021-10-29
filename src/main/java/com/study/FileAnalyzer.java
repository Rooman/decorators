package com.study;

import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileAnalyzer {
    private static final String EXCEPTION_IN_STREAM = "Something went wrong in 'readContent(String path)')!";
    private static final int BUFFER_SIZE = 8192; // 8KB
    private static final String UTF = "UTF-8";
    private static final String REGEX = "((?<=\\.)|(?<=\\?)|(?<=!))";

    public static Values countMatches(String path, String word) {
        var content = readContent(path);
        var sentences = split(content);
        var sentencesWithWord = filterWord(sentences, word);
        var countWord = countWord(sentencesWithWord, word);

        return new Values(countWord, sentencesWithWord);
    }

    static String readContent(String path) {
        String content;

        try (var inputStream = new FileInputStream(path);
             var bufferedReader = new BufferedReader(
                     new InputStreamReader(inputStream, UTF), BUFFER_SIZE)) {

            content = bufferedReader.lines().parallel().collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_IN_STREAM, e);
        }
        return content;
    }

    static List<String> split(String content) {
        return Arrays.stream(content.split(REGEX))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    static List<String> filterWord(List<String> sentences, String word) {
        return sentences.stream()
                .filter(e -> StringUtils.containsIgnoreCase(e, word))
                .collect(Collectors.toList());
    }

    static int countWord(List<String> sentences, String word) {
        return (int) Pattern
                .compile(word.toLowerCase())
                .matcher(sentences.stream()
                        .flatMap(String::lines)
                        .map(String::toLowerCase)
                        .collect(Collectors.joining()))
                .results().count();
    }

    @Value
    static class Values {
        int count;
        List<String> sentencesWithWord;
    }
}
