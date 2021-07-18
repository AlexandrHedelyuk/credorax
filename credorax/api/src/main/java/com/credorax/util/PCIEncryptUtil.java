package com.credorax.util;

import java.util.Base64;

public class PCIEncryptUtil {


    public static String encrypt(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
