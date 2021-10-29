package com.study;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileAnalyzerTest {
    private static final String PATH = "testFile.text";
    private static final String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
            "eiusmod tempor incididunt ut labore et dolore magna aliqua. Molestie at elementum eu facilisis " +
            "sed odio morbi quis commodo. In mollis nunc sed id semper risus in hendrerit. Vestibulum lectus " +
            "mauris ultrices eros in cursus. Eu augue ut lectus arcu bibendum. Nunc lobortis mattis aliquam " +
            "faucibus purus in massa tempor. Libero enim sed faucibus turpis in eu. Pharetra convallis posuere " +
            "morbi leo urna molestie at. Donec et odio pellentesque diam volutpat commodo sed. Consequat ac " +
            "donec et odio pellentesque diam. Pellentesque elit eget gravida cum sociis natoque penatibus. In " +
            "fermentum posuere urna nec. At elementum eu facilisis sed. Accumsan lacus vel facilisis est velit.";

    String word;


    @BeforeAll
    static void beforeAll() throws IOException {
        File file = new File(PATH);
        if (!file.createNewFile()) {
            file.delete();
            file.createNewFile();
            System.out.println("recreated file");
        }
        fulfillFile(PATH, TEXT);
    }

    @AfterAll
    static void afterAll() {
        new File(PATH).delete();
    }

    @Test
    @DisplayName("When call 'readContent()' is called then correct String is returned")
    void whenCallReadContentIsCalledThenCorrectStringIsReturned() throws IOException {
         assertEquals(TEXT, FileAnalyzer.readContent(PATH));
    }

    @Test
    @DisplayName("Given path and word when ")
    void givenPathAndWordWhen() throws IOException {

        word = "some";
        File file = new File(PATH);
        file.createNewFile();
        int expected = 2;
        int actual = FileAnalyzer.getOccurrences(PATH, word);

        assertEquals(expected, actual);
    }

    static void fulfillFile(String path,String text) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text.getBytes());
        }
    }

}