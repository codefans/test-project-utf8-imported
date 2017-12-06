package com.codefans.reusablecode.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import io.jsonwebtoken.impl.TextCodec;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: caishengzhi
 * @date: 2017-12-06 14:15
 **/
public class TokenUtilTest {

    @Test
    public void base64EncodeTest() {

        try {

            Map<String, Object> headerMap = new HashMap<String, Object>();
            headerMap.put("typ","JWT");
            headerMap.put("alg","HS256");

            /**
             {
             "typ": "JWT",
             "alg": "HS256"
             }
             eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
             */
//            ObjectMapper om = new ObjectMapper();
//            String hdStr = om.writeValueAsString(headerMap);
//            System.out.println("headerStr:");
//            System.out.println(hdStr);
//            String headerStr = TextCodec.BASE64URL.encode(hdStr);
//            System.out.println(headerStr);
//            System.out.println(headerStr.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"));

            //body
            /**
             {
             "iss": "ninghao.net",
             "exp": "1438955445",
             "name": "wanghao",
             "admin": true
             }
             eyJpc3MiOiJuaW5naGFvLm5ldCIsImV4cCI6IjE0Mzg5NTU0NDUiLCJuYW1lIjoid2FuZ2hhbyIsImFkbWluIjp0cnVlfQ
             */
            Map<String, Object> bodyMap = new HashMap<String, Object>();
            bodyMap.put("iss","JWT");
            bodyMap.put("exp",System.currentTimeMillis());
            bodyMap.put("name","wanghao");
            bodyMap.put("admin",true);

            String token = TokenUtil.generateToken(headerMap, bodyMap);
//            System.out.println("token:");
//            System.out.println(token);

            TokenUtil.parseToken(token);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
