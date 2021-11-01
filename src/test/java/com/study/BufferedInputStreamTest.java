package com.study;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BufferedInputStreamTest {
    @Test
    public void testReadFiveBytes() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[]{65, 66, 67, 68, 69});
        BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
        assertEquals(65, bufferedInputStream.read());
        assertEquals(66, bufferedInputStream.read());
        assertEquals(67, bufferedInputStream.read());
        assertEquals(68, bufferedInputStream.read());
        assertEquals(69, bufferedInputStream.read());
        assertEquals(-1, bufferedInputStream.read());

        // read, read(byte[])
        // write(int), write(byte[])
        // close
        // flush
    }
}
