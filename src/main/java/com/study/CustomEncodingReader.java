package com.study;

public class CustomEncodingReader implements Reader {
    private Reader target;

    public CustomEncodingReader(Reader target) {
        this.target = target;
    }

    @Override
    public int read() {
        int originalChar = target.read();
        if (originalChar == -1) {
            return -1;
        }
        int reincodedChar = originalChar + 32;
        return reincodedChar;
    }

    @Override
    public void close() {
        target.close();
    }
}
