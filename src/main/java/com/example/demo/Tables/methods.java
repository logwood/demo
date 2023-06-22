package com.example.demo.Tables;

import org.apache.commons.codec.DecoderException;

public class methods {

    public static String bytesToString(byte[] bytes) {
        //转换成hex
        return org.apache.commons.codec.binary.Hex.encodeHexString(bytes);
    }
    public static byte[] stringToByte(String str) throws DecoderException {
        //转换成hex
        return org.apache.commons.codec.binary.Hex.decodeHex(str);
    }
}
