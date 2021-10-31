package com.study;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {
    private static final String FROM = "TempDir#";
    private static final String FROM_WITH_INDEX = "TempDir#0";

    @SneakyThrows
    @BeforeEach
    void setUp() {
        createTestDirectoriesWithFiles();
    }

    @AfterEach
    void clear() {
        clear(FROM_WITH_INDEX);
    }

    @Test
    @DisplayName("When call 'countFiles(String path)' then number of files returned")
    void whenCallCountFilesStringPathThenNumberOfFilesReturned() {
        assertEquals(8, FileManager.countFiles("TempDir#0"));
    }

    @Test
    @DisplayName("When call 'countDirs(String path)' then number of dirs returned")
    void whenCallCountDirsStringPathThenNumberOfDirsReturned() {
        assertEquals(4, FileManager.countDirs("TempDir#0"));
    }

    @Test
    @DisplayName("When call 'copy(from, to)' from -> file, to dir - doesn't exists")
    void whenCallCopyFromToFromFileToDoesntExists() {
        String from = "TempDir#0/temp#0.file";
        String to = "TempDir#1/temp#0.file";

        FileManager.copy(from, to);
        assertTrue(new File(to).exists());
        clear("TempDir#1");
        assertFalse(new File("TempDir#1").exists());
    }

    @Test
    @DisplayName("When call 'copy(from, to)' from -> empty dir, to dir - doesn't exists")
    void whenCallCopyFromToFromEmptyDirToDirDoesntExists() {
        String from = "TempDir#0";
        String to = "TempDir#1";

        FileManager.copy(from, to);
        assertTrue(new File(to).exists());
        clear(to);
        assertFalse(new File(to).exists());

    }

    @Test
    @DisplayName("When call 'copy(from, to)' from -> dir with subdirs with files, to dir - doesn't exists")
    void whenCallCopyFromToFromDirWithSubDirsToDirDoesntExists() {
        String from = "TempDir#0";
        String to = "TempDir#33";

        FileManager.copy(from, to);
        assertTrue(new File("TempDir#33/TempDir#1/TempDir#2/TempDir#3/temp#0.file")
                .exists());
        clear(to);
        assertFalse(new File(to).exists());
    }

    @Test
    @DisplayName("When call 'move(from, to)' then from file or directory deletes")
    void whenCallMoveFromToThenFromFileOrDirectoryDeletes() {
        String from = "TempDir#0";
        String to = "TempDir#33";
        FileManager.move(from, to);
        assertTrue(new File("TempDir#33/TempDir#1/TempDir#2/TempDir#3/temp#0.file")
                .exists());
        assertFalse(new File(from).exists());
        clear(to);
        assertFalse(new File(to).exists());
    }

    @SneakyThrows
    private void createTestDirectoriesWithFiles() {
        StringBuilder path = new StringBuilder(new File(".").getCanonicalPath());
        for (int i = 0; i < 4; i++) {
            path.append(File.separator).append(FileManagerTest.FROM).append(i);
            new File(path.toString()).mkdir();
            for (int j = 0; j < 2; j++) {
                path.append(File.separator);
                new File(path + "temp#" + j + ".file")
                        .createNewFile();
            }
        }
    }

    void clear(String from) {
        File file = new File(from);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            for (String naming : file.list()) {
                String innerPathFrom = from + File.separator + naming;
                clear(innerPathFrom);
                file.delete();
            }
        }
    }
}