package com.study;

public class BufferedReader implements Reader {
    private Reader target;
    private int[] buffer = new int[100];
    private int index;
    private int count;

    public BufferedReader(Reader target) {
        this.target = target;
    }

    @Override
    public int read() {
        if (count == 0 || index == count) {
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = target.read();
            }
            count = buffer.length;
        }
        int value = buffer[index];
        index++;
        return value;
    }

    @Override
    public void close() {
        buffer = null;
        target.close();
    }

    public String readString(int amount) {
        byte[] bytes = new byte[amount];
        for (int i = 0; i < amount; i++) {
            bytes[i] = (byte) read();
        }
        return new String(bytes);
    }
}
