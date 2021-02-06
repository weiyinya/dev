package com.wy.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.channels.FileChannel.MapMode.READ_WRITE;

/**
 * @author Dwen
 * @date 2021-01-25 19:06
 */
public class FileTest {

    private final static String CONTENT = "Zero copy implemented by MappedByteBuffer";
    private final static String FILE_NAME = "/mmap.txt";
    private final static String CHARSET = "UTF-8";

    public static void main(String[] args) {
        Path path = Paths.get(FileTest.class.getResource(FILE_NAME).getPath());
        byte[] bytes = CONTENT.getBytes(Charset.forName(CHARSET));
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(READ_WRITE, 0, bytes.length);
            if (mappedByteBuffer != null) {
                mappedByteBuffer.put(bytes);
                mappedByteBuffer.force();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
