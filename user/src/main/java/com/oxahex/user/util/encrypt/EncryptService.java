package com.oxahex.user.util.encrypt;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Service
public class EncryptService {

    private static final String CIPHER_INSTANCE = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "encryptsecretkey";

    public String encryptText(String text)
            throws GeneralSecurityException
    {

        byte[] bytes = getCipher(Cipher.ENCRYPT_MODE, SECRET_KEY)
                        .doFinal(text.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(bytes);
    }

    public String decryptString(String text)
            throws GeneralSecurityException {

        byte[] bytes = Base64.getDecoder()
                .decode(text.getBytes(StandardCharsets.UTF_8));

        return new String(
                getCipher(Cipher.DECRYPT_MODE, SECRET_KEY).doFinal(bytes),
                StandardCharsets.UTF_8
        );
    }

    private static Cipher getCipher(int mode, String secretKey)
            throws GeneralSecurityException
    {
        Cipher c = Cipher.getInstance(CIPHER_INSTANCE);
        SecretKey sk = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec iv = new IvParameterSpec(secretKey.substring(0, 16).getBytes(StandardCharsets.UTF_8));

        c.init(mode, sk, iv);

        return c;
    }
}
