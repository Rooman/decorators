package com.study;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileAnalyzerTest {
    private static final String PATH = "testFile.text";
    private static final String TEXT = "Lorem ipsum dolor sit amet consectetur adipiscing elit, sed do " +
            "eiusmod tempor incididunt ut labore et dolore magna aliqua! Molestie at elementum eu facilisis " +
            "sed odio morbi quis commodo. In mollis nunc sed id semper risus in hendrerit. Vestibulum lectus " +
            "mauris ultrices eros in cursus. Eu augue ut lectus arcu bibendum. Nunc lobortis mattis aliquam " +
            "faucibus purus in massa tempor? Libero enim sed faucibus turpis in eu. Pharetra convallis posuere " +
            "morbi leo urna molestie at. Donec et odio pellentesque diam volutpat commodo sed. Consequat ac " +
            "donec et odio pellentesque diam. Pellentesque elit eget gravida cum sociis natoque penatibus. In " +
            "fermentum posuere urna nec. At elementum eu facilisis sed! Accumsan lacus vel facilisis est velit?";


    @BeforeAll
    static void beforeAll() throws IOException {
        File file = new File(PATH);
        if (!file.createNewFile()) {
            file.delete();
            file.createNewFile();
            System.out.println("Recreating file!!!!");
        }
        fulfillFile();
    }

    @AfterAll
    static void afterAll() {
        new File(PATH).delete();
    }

    @Test
    @DisplayName("When call 'readContent(path)' then correct String is returned")
    void whenCallReadContentIsCalledThenCorrectStringIsReturned() throws IOException {
         assertEquals(TEXT, FileAnalyzer.readContent(PATH));
    }

    @Test
    @DisplayName("When call 'split(content)' method then correct list of sentences is returned")
    void whenCallSplitMethodThenCorrectListOfSentencesIsReturned() {
        List<String> expected = List.of("Lorem ipsum dolor sit amet consectetur adipiscing elit, sed do " +
                "eiusmod tempor incididunt ut labore et dolore magna aliqua!", "Molestie at elementum eu facilisis " +
                "sed odio morbi quis commodo.", "In mollis nunc sed id semper risus in hendrerit.", "Vestibulum lectus " +
                "mauris ultrices eros in cursus.", "Eu augue ut lectus arcu bibendum.", "Nunc lobortis mattis aliquam " +
                "faucibus purus in massa tempor?", "Libero enim sed faucibus turpis in eu.", "Pharetra convallis posuere " +
                "morbi leo urna molestie at.", "Donec et odio pellentesque diam volutpat commodo sed.", "Consequat ac " +
                "donec et odio pellentesque diam.", "Pellentesque elit eget gravida cum sociis natoque penatibus.", "In " +
                "fermentum posuere urna nec.", "At elementum eu facilisis sed!", "Accumsan lacus vel facilisis est velit?");
        assertEquals(expected, FileAnalyzer.split(TEXT));
    }
    @Test
    @DisplayName("Given path and word when ")
    void givenPathAndWordWhen() throws IOException {

        String word = "sed";
        File file = new File(PATH);
        file.createNewFile();
        int expected = 2;
        int actual = FileAnalyzer.countMatches(PATH, word);

        assertEquals(expected, actual);
    }

    static void fulfillFile() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(FileAnalyzerTest.PATH)) {
            fos.write(FileAnalyzerTest.TEXT.getBytes());
        }
    }
}