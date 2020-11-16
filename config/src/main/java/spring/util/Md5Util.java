package spring.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class Md5Util {

    private static String salt="";

    public static   String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //str=str+"czkjyxgs"; 以后再加盐把
        //确定计算方法
        //MessageDigest md5=MessageDigest.getInstance("MD5");
        //BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        //byte[] nihao =md5.digest(str.getBytes("utf-8"));
        //String newstr=base64en.encode();
        String md5Pass = DigestUtils.md5DigestAsHex((str+salt).getBytes());
        return md5Pass;
    }

}
