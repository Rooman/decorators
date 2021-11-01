package com.study;

import java.util.Random;

public class FileReader implements Reader {
    @Override
    public int read() {
        Random random = new Random();
        // 65 -> 92
        boolean isClosed = random.nextInt(100) == 0;

        int value = random.nextInt(27) + 65; // 65 => A

        return isClosed ? -1 : value;
    }

    public void close() {
        System.out.println("FileReader is closed");
    }
}
