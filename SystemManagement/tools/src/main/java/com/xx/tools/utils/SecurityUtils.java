package com.xx.tools.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SecurityUtils {
    private static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    public static String encrypt(String sSrc, String safeCode) {
        try {
            if (StringUtils.isEmpty(sSrc)) {
                return "";
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = safeCode.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(safeCode.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String decrypt(String sSrc, String safeCode) {
        try {
            if (StringUtils.isEmpty(sSrc)) {
                return "";
            }
            byte[] raw = safeCode.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(safeCode.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "UTF-8");
            return originalString;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
