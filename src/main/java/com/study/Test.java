package com.study;

public class Test {
    public static void main(String[] args) {
        Reader reader = new FileReader();
        BufferedReader bufferedReader =
                new BufferedReader(new CustomEncodingReader(reader));
//        readContent(bufferedReader);
        int count = 10;
        String value = bufferedReader.readString(count);
        System.out.println(value);
    }

    static void readContent(Reader reader) {
        int value;
        while ((value = reader.read()) != -1) {
            System.out.print((char) value);
        }
    }
}
