package com.codefans.reusablecode.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.*;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * @author: caishengzhi
 * @date: 2017-12-06 14:15
 **/
public class TokenUtil {

    //Create a legitimate RSA public and private key pair:
    private static KeyPair kp = RsaProvider.generateKeyPair(1024);
    private static PublicKey publicKey = kp.getPublic();
    private static PrivateKey privateKey = kp.getPrivate();
    private static ObjectMapper objMapper = new ObjectMapper();

    /**
     * header部分
     * 例如：
     {
         "typ": "JWT",
         "alg": "HS256"
     }
     */
    private String header;

    /**
     * token的具体内容
     * 例如：
     {
         "iss": "ninghao.net",
         "exp": "1438955445",
         "name": "wanghao",
         "admin": true
     }
     */
    private String playload;

    public static String generateToken(Map<String, Object> headerMap, Map<String, Object> bodyMap) throws JsonProcessingException, InvalidKeyException, NoSuchAlgorithmException {
        return generateToken(objMapper.writeValueAsString(headerMap), objMapper.writeValueAsString(bodyMap));
    }


    public static String generateToken(String header, String playload) throws NoSuchAlgorithmException, InvalidKeyException {
        StringBuffer sb = new StringBuffer();


        String headerStr = base64UrlEncode(header);
        String playloadStr = base64UrlEncode(playload);

        System.out.println("header:");
        System.out.println(header);
        System.out.println("headerEncode:");
        System.out.println(headerStr);

        System.out.println("playload:");
        System.out.println(playload);
        System.out.println("playloadStr:");
        System.out.println(playloadStr);

        String compact = headerStr + "." + playloadStr;

        Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(publicKey.getEncoded(), "HmacSHA256"));
//        mac.init(new SecretKeySpec("secret".getBytes(), "HmacSHA256"));
        byte[] signatureBytes = mac.doFinal(compact.getBytes(Charset.forName("US-ASCII")));
        String encodedSignature = TextCodec.BASE64URL.encode(signatureBytes);
        System.out.println("encodedSignature:");
        System.out.println(encodedSignature);

        System.out.println("token:");
        String token = compact + "." + encodedSignature;
        System.out.println(token);
        sb.append(token);

        return sb.toString();
    }

    public static void parseToken(String token) {
        // Assert that the server (that should always use the private key) does not recognized the forged token:
        try {
//            Jwt jwt = Jwts.parser().setSigningKey(privateKey).parse(token);
            SecretKey secretKey = new SecretKeySpec(publicKey.getEncoded(), "HmacSHA256");
            Jwt jwt = Jwts.parser().setSigningKey(secretKey).parse(token);
            Header headerParse = jwt.getHeader();
            Map<String, Object> body = (Map<String, Object>)jwt.getBody();
            String headerStr = objMapper.writeValueAsString(headerParse);
            String bodyStr = objMapper.writeValueAsString(body);
            System.out.println("headerDecodeStr:");
            System.out.println(headerStr);
            System.out.println("bodyDecodeStr:");
            System.out.println(bodyStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String base64UrlEncode(String data) {
        return TextCodec.BASE64URL.encode(data);
    }

    public static String base64Encode(String str) throws UnsupportedEncodingException {
        return new BASE64Encoder().encode(str.getBytes("UTF-8"));
    }

    public static String base64Decode(String str) throws IOException {
        byte[] bytes = new BASE64Decoder().decodeBuffer(str);
        return new String(bytes, "UTF-8");
    }


}
