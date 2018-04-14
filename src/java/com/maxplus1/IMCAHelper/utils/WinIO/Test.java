package com.maxplus1.IMCAHelper.utils.WinIO;

import com.sun.jna.NativeLibrary;

public class Test {
    public static void main(String[] args) {
        String path = Test.class.getResource("/WinIO").getPath();
        System.out.println(path);
        NativeLibrary.addSearchPath("WinIo64", path);
    }
}
