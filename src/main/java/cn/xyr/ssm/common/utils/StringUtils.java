package cn.xyr.ssm.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author XYR
 * @time 2018/11/22 16:11
 */
public class StringUtils {
    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = "x@y&r";

    /**
     * 判断字符串是否为空(null,"","null")
     *
     * @param str 要判断是字符串
     * @return 是否为空
     */
    public static boolean isNotNull(String str) {
        return str != null && !str.equals("") && !str.equals("null");
    }


    /**
     * 获取加密后的密码
     * 加密算法
     * plainText=明文密码+盐
     * 将plainText进行MD5摘要后再base64编码后再连接盐值作为plainText
     * 循环7次
     *
     * @param password 明文密码
     * @param salt     盐值
     * @return 密文
     */
    public static String getEncryptPassword(String password, String salt) {
        String cipherText = null;//加密混淆后的密码
        try {
            if (isNotNull(password) && isNotNull(salt)) {
                String plainText = password + salt;
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                for (int i = 0; i < 7; i++) {//加密7次
                    cipherText = Base64.encodeBase64String(messageDigest.digest(plainText.getBytes()));
                    plainText = cipherText + salt;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }


    /**
     * 随机获取盐值
     *
     * @return 随机盐值
     */
    public static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 获取随机文件名
     * 算法要根据用户数来修改
     * 算法为时间戳+ 1-10000随机数
     *
     * @return 随机文件名
     */
    public static String getFile() {
        SecureRandom random = new SecureRandom();
        return "" + new Date().getTime() + random.nextInt(10000);
    }

    /**
     * 获取随机订单号
     * 算法为 日期+6位随机数
     *
     * @return 随机订单号
     */
    public static String getOrderNO() {
        StringBuilder sb = new StringBuilder();
        sb.append(formatDateToString(new Date(), "yyyyMMddhhmmss"));
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 时间转字符串
     *
     * @param date   时间
     * @param format 目标格式
     * @return 时间字符串
     */
    public static String formatDateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }


    /**
     * 字符串拼接
     *
     * @param strs 字符串
     * @return 字符串
     */
    public static String getString(String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 字符串拼接
     * @param objs 对象
     * @return 字符串
     */
    public static String getString(Object... objs) {
        StringBuilder sb = new StringBuilder();
        for (Object o : objs) {
            sb.append(o);
        }
        return sb.toString();
    }
}
