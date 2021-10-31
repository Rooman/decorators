package com.study;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import static com.study.FileAnalyzer.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileAnalyzerTest {
    private static final String PATH = "testFile.text";
    private static final String TEXT = "Если бы в Java действительно работала сборка мусора, большинство программ бы удаляли сами себя при первом же запуске. " +
            "Если вы начинаете сгонять муху с монитора при помощи курсора мыши, пора выключать компьютер. " +
            "Измерять продуктивность программиста подсчетом строк кода — это так же, как оценивать постройку самолета по его весу? " +
            "Java — это C++, из которого убрали все пистолеты, ножи и дубинки!";
    private static final List<String> SENTENCES = List.of(
            "Если бы в Java действительно работала сборка мусора, большинство программ бы удаляли сами себя при первом же запуске.",
            "Если вы начинаете сгонять муху с монитора при помощи курсора мыши, пора выключать компьютер.",
            "Измерять продуктивность программиста подсчетом строк кода — это так же, как оценивать постройку самолета по его весу?",
            "Java — это C++, из которого убрали все пистолеты, ножи и дубинки!");
    private static final String WORD_ENG = "Java";
    private static final String WORD_RUS = "программ";

    @BeforeAll
    static void beforeAll() {
        createNewFileAndFillIn();
    }

    @AfterAll
    static void afterAll() {
        deleteFile();
    }

    @Test
    @DisplayName("When call 'readContent(path)' then correct String is returned")
    void whenCallReadContentIsCalledThenCorrectStringIsReturned() {
        assertEquals(TEXT, readContent(PATH));
    }

    @Test
    @DisplayName("When call 'split(content)' method then correct list of sentences is returned")
    void whenCallSplitMethodThenCorrectListOfSentencesIsReturned() {
        System.out.println(split(TEXT));
        assertEquals(SENTENCES, split(TEXT));
    }

    @Test
    @DisplayName("When call 'filterWorld(sentences, word) then list of sentences containing word is returned")
    void whenCallFilterWorldSentencesWordThenListOfSentencesContainingWordIsReturned() {
        List<String> expected = List.of(SENTENCES.get(0), SENTENCES.get(3));
        System.out.println("ex" +expected);
        System.out.println("fi" + filterWord(SENTENCES, WORD_ENG));
        assertEquals(expected, filterWord(SENTENCES, WORD_ENG));
        expected = List.of(SENTENCES.get(0), SENTENCES.get(2));
        assertEquals(expected, filterWord(SENTENCES, WORD_RUS));
    }

    @Test
    @DisplayName("When call 'countMatches(path, word)' then number of occurrence is returned")
    void whenCallCountMatchesPathWordThenNumberOfOccurrenceIsReturned() {
        Values values = countMatches(PATH, WORD_ENG);
        Values values2 = countMatches(PATH, WORD_RUS);
        assertEquals(2, values.getCount());
        assertEquals(2, values2.getCount());
        assertEquals(List.of(SENTENCES.get(0), SENTENCES.get(3)), values.getSentencesWithWord());
        assertEquals(List.of(SENTENCES.get(0), SENTENCES.get(2)), values2.getSentencesWithWord());
    }

    @Test
    @DisplayName("When call 'countWord()' with russian word then correct occurrence returns")
    void whenCallCountWordThenCorrectOccurrenceReturn() {
        int expected = 4;
        int actual = countWord(List.of("Время have some время, а ты нет!", "Программа нужно временная вовремя прививка?"), "врем");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When call 'countWord()' with english word then correct occurrence returns")
    void whenCallCountWordThenCorrectOccurrenceReturnEng() {
        int expected = 5;
        int actual = countWord(List.of("Lasdf java lkasdjjavalaksdfj kjd kdkd ss!", "asdf asfd  adsf ?", "Javafsldf sdlkfjavakdljf *(*(java"), "JAVA");
        assertEquals(expected, actual);
    }

    @SneakyThrows
    private static void createNewFileAndFillIn() {
        File file = new File(PATH);
        if (!file.createNewFile()) {
            file.delete();
            file.createNewFile();
        }
        fillInFile();
    }

    @SneakyThrows
    private static void fillInFile() {
        try (FileOutputStream fos = new FileOutputStream(FileAnalyzerTest.PATH)) {
            fos.write(FileAnalyzerTest.TEXT.getBytes());
        }
    }

    private static void deleteFile() {
        new File(PATH).delete();
    }
}