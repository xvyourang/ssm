import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;


/**
 * @author xyr
 * AES128 算法
 * <p>
 * CBC 模式
 * <p>
 * PKCS7Padding 填充模式
 * <p>
 * CBC模式需要添加一个参数iv
 * <p>
 * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding 没有什么区别
 * 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
 */
public class AES {
    // 算法名称
    private static final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    private static final String algorithmStr = "AES/CBC/PKCS7Padding";
    //

    public static void main(String[] args) {
        byte[] iv = {0x30, 0x31, 0x30, 0x32, 0x30, 0x33, 0x30, 0x34, 0x30, 0x35, 0x30, 0x36, 0x30, 0x37, 0x30, 0x38};
        byte[] keyBytes = {0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38};
        String content = "19941002";
        byte[] enc = encrypt(content.getBytes(), keyBytes, iv);
        byte[] dec = decrypt(enc, keyBytes, iv);
        System.out.println("明文：" + content + "\r\n密文：" + new String(Hex.encode(enc)) + "\r\n解密后" + new String(dec));
        System.out.println("秘钥" + new String(keyBytes) + "\r\n解密向量" + new String(iv));
        System.out.println(Arrays.toString(enc));
    }

    private static byte[] init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + 1;
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        return keyBytes;
    }

    /**
     * 加密方法
     *
     * @param content  要加密的字符串
     * @param keyBytes 加密密钥
     * @param iv 加密向量
     * @return 加密后的bytes
     */
    public static byte[] encrypt(byte[] content, byte[] keyBytes, byte[] iv) {
        byte[] encryptedText = null;
        keyBytes = init(keyBytes);
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        Cipher cipher = null;
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            encryptedText = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param iv 解密向量
     * @return 解密后的bytes
     */
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, byte[] iv) {
        byte[] encryptedText = null;
        keyBytes = init(keyBytes);
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        Cipher cipher = null;
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }
}
