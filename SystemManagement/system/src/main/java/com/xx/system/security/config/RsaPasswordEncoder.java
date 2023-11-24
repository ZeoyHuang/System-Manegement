package com.xx.system.security.config;

import com.xx.tools.utils.RsaUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;

import static com.xx.tools.utils.RsaUtils.sign;

/***
 * @title RsaPasswordEncoder
 * @description <description class purpose>
 * @author WeiShuo
 * @version 1.0.0
 * @create 2023/8/14 21:06
 **/
public class RsaPasswordEncoder implements PasswordEncoder {


        static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIb3B054H+Yw87yezuwaV0M+TXthoBmWtKHKer5M9u1/t/0/5ZwBDEaWnwowPyhkHWsZk7EPaL8Lgsl0h4/DUgJrH50y6ZvYmVgulxD3EDe46hywzBO62hT2wxEbARmN+8j//1xkTYXVxu7sbNV5d+4SxASe29OBqrukYys+NKuJAgMBAAECgYBh7vCDVRE4lH7YeJgHpNl7NsM8a0ukJcIuwGEuo2RuU8XrYyk2eWAx/GutFfNOWM8r/uQ3j8nfDvg5PHB9tipT9xxCnFgwOeMB2+/ofOXeicKI7UFyW1LWbEYOKe3QQmzWa3vXCn2LsVnBAHcbdHlz134wKr7VJc+t1QeU03EKgQJBAO0zmzFyDIk2iBJOxR5bRWMBmK6I7S9E364X3lEwOkK/WtTF+9H0E6tojxoGAnuLDI+K+Zf0xu5d5YARlWmlYXkCQQCRqTmQGjo9BGGpr2u0oF04yNek3G9e9ysXwQktDPmgnn4GsSrrp3mcb+Z1/2xfmW2fWwCcWiCSFwAo3YSb9KaRAkAB7dqEQ24wq33d0EAwKAPfc0LfoIN1T/UVwGHxfRfsNQwzEM0kfvyt9zK6vnPEt3PJsxKmlroLdD4KlZoGeu7ZAkAmD8cn3YKcURnIAjutrj3NycV3odZERWfwRBPGvt4311JtIzxo6ZFAjIj3CnBiJrBbdKcbM/3QzsvO4dt1+R7RAkEAgMDvRc3Um1mQqt3xoxT3VYUEh8LQxF72x+dsXrPzoggFLvCXSlA7WQJpyDqHK8x2HSXvazB82Ei1uro58sd0Hw==";

        static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG9wdOeB/mMPO8ns7sGldDPk17YaAZlrShynq+TPbtf7f9P+WcAQxGlp8KMD8oZB1rGZOxD2i/C4LJdIePw1ICax+dMumb2JlYLpcQ9xA3uOocsMwTutoU9sMRGwEZjfvI//9cZE2F1cbu7GzVeXfuEsQEntvTgaq7pGMrPjSriQIDAQAB";

        @Override
        public String encode(CharSequence rawPassword) {

                String encryptData;
                // RSA加密
                try {
                       encryptData  = RsaUtils.encrypt(String.valueOf(rawPassword), RsaUtils.getPublicKey(publicKey));
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                return encryptData;
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {

                // RSA签名
                String sign = null;
                // RSA验签
                boolean result = false;
                try {
                        // RSA解密
                        String decryptData = RsaUtils.decrypt(encodedPassword, RsaUtils.getPrivateKey(privateKey));
                        System.out.println("解密后内容:" + decryptData);

                        sign = sign(decryptData, RsaUtils.getPrivateKey(privateKey));
                        result =RsaUtils.verify(String.valueOf(rawPassword), RsaUtils.getPublicKey(publicKey), sign);
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
                return result;

        }

        public static void main(String[] args) {
                try {
                        // 生成密钥对
                        KeyPair keyPair = RsaUtils.getKeyPair();
//            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
                        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIb3B054H+Yw87yezuwaV0M+TXthoBmWtKHKer5M9u1/t/0/5ZwBDEaWnwowPyhkHWsZk7EPaL8Lgsl0h4/DUgJrH50y6ZvYmVgulxD3EDe46hywzBO62hT2wxEbARmN+8j//1xkTYXVxu7sbNV5d+4SxASe29OBqrukYys+NKuJAgMBAAECgYBh7vCDVRE4lH7YeJgHpNl7NsM8a0ukJcIuwGEuo2RuU8XrYyk2eWAx/GutFfNOWM8r/uQ3j8nfDvg5PHB9tipT9xxCnFgwOeMB2+/ofOXeicKI7UFyW1LWbEYOKe3QQmzWa3vXCn2LsVnBAHcbdHlz134wKr7VJc+t1QeU03EKgQJBAO0zmzFyDIk2iBJOxR5bRWMBmK6I7S9E364X3lEwOkK/WtTF+9H0E6tojxoGAnuLDI+K+Zf0xu5d5YARlWmlYXkCQQCRqTmQGjo9BGGpr2u0oF04yNek3G9e9ysXwQktDPmgnn4GsSrrp3mcb+Z1/2xfmW2fWwCcWiCSFwAo3YSb9KaRAkAB7dqEQ24wq33d0EAwKAPfc0LfoIN1T/UVwGHxfRfsNQwzEM0kfvyt9zK6vnPEt3PJsxKmlroLdD4KlZoGeu7ZAkAmD8cn3YKcURnIAjutrj3NycV3odZERWfwRBPGvt4311JtIzxo6ZFAjIj3CnBiJrBbdKcbM/3QzsvO4dt1+R7RAkEAgMDvRc3Um1mQqt3xoxT3VYUEh8LQxF72x+dsXrPzoggFLvCXSlA7WQJpyDqHK8x2HSXvazB82Ei1uro58sd0Hw==";
//            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
                        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG9wdOeB/mMPO8ns7sGldDPk17YaAZlrShynq+TPbtf7f9P+WcAQxGlp8KMD8oZB1rGZOxD2i/C4LJdIePw1ICax+dMumb2JlYLpcQ9xA3uOocsMwTutoU9sMRGwEZjfvI//9cZE2F1cbu7GzVeXfuEsQEntvTgaq7pGMrPjSriQIDAQAB";
                        System.out.println("私钥:" + privateKey);
                        System.out.println("公钥:" + publicKey);
                        // RSA加密
//                        String data = "test";
                        String data = "123456";
                        String encryptData = RsaUtils.encrypt(data, RsaUtils.getPublicKey(publicKey));
                        System.out.println("加密后内容:" + encryptData);
                        /*// RSA解密
                        String decryptData = RsaUtils.decrypt(encryptData, RsaUtils.getPrivateKey(privateKey));
                        System.out.println("解密后内容:" + decryptData);*/
                        // RSA签名
//                        String sign = sign(data, RsaUtils.getPrivateKey(privateKey));
                        String sign = sign("123", RsaUtils.getPrivateKey(privateKey));
                        // RSA验签
                        boolean result = RsaUtils.verify(data, RsaUtils.getPublicKey(publicKey), sign);
                        System.out.print("验签结果:" + result);
                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.print("加解密异常");
                }
        }

}
