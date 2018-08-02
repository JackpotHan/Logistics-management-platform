package com.xiaohan.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:46
 * @Description:
 */
public class Zlib {
    public static byte[] encode(byte[] data) {
        final Deflater deflater = new Deflater();
        deflater.setInput(data);

        deflater.finish();
        final byte[] bytesCompressed = new byte[Short.MAX_VALUE];
        final int numberOfBytesAfterCompression = deflater.deflate(bytesCompressed);
        final byte[] returnValues = new byte[numberOfBytesAfterCompression];
        System.arraycopy(bytesCompressed, 0, returnValues, 0, numberOfBytesAfterCompression);
        return returnValues;
    }

    public static String encodeToBase64(byte[] data) {
        return Base64.encode(encode(data));
    }

    public static byte[] decode(byte[] data) {
        byte[] output;
        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);

        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        decompresser.end();
        return output;
    }

    public static String decodeToString(byte[] data) {
        return new String(decode(data));
    }
}
