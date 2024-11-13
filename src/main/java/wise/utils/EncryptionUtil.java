package wise.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import wise.common.CustomException;

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private SecretKey secretKey;
    // Define a static IV as a 16-byte array (128 bits)
    private static final String FIXED_IV = "0123456789ABCDEF"; // Example fixed IV in hexadecimal or any 16-character string

    // Inject the Base64-encoded AES key from application.properties
    @Value("${encryption.key}")
    private String base64EncodedKey;

    // Initialize the SecretKey after dependency injection
    @PostConstruct
    private void init() {
        byte[] decodedKey = Base64.getDecoder().decode(base64EncodedKey);
        this.secretKey = new SecretKeySpec(decodedKey, ALGORITHM);
    }

    // Generates a random Initialization Vector (IV) for CBC mode
    public static IvParameterSpec generateIv() {
        // byte[] iv = new byte[16];
        // new java.security.SecureRandom().nextBytes(iv);
        // return new IvParameterSpec(iv);
        return new IvParameterSpec(FIXED_IV.getBytes());
    }

    // Encrypts the input data using the provided key and IV
    public String encrypt(String plainText) {
        try {
            IvParameterSpec iv = generateIv();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

            // Encode both IV and cipherText in Base64 for storage
            // String ivString = Base64.getEncoder().encodeToString(iv.getIV());
            String cipherText = Base64.getEncoder().encodeToString(encryptedBytes);
            // return cipherText + ":" + ivString; // Store IV and cipherText together
            return cipherText ; // Store IV and cipherText together
        } catch (Exception e) {
            // throw new CustomException("error.encrypt_cache");
            throw new CustomException(e.getMessage());
        }
        
    }

    // Decrypts the encrypted data using the provided key and IV
    public String decrypt(String cipherTextWithIv) {
        try {
            String[] parts = cipherTextWithIv.split(":");
            // IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
            IvParameterSpec iv = generateIv();
            byte[] cipherText = Base64.getDecoder().decode(parts[0]);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] decryptedBytes = cipher.doFinal(cipherText);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new CustomException("error.decrypt_cache");
        }
        
    }
}
