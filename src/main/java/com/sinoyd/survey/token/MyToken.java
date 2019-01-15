package com.sinoyd.survey.token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 用来生成Token以及解析Token
 * @auther 李忠杰
 * @create 2018-12-27 14:07
 */
public class MyToken {

    private static final String TOKEN_KEY = "sinoyd";               //用这个StringKey来作为密文进行测试

    public static String createJWT(String userName,long ttlMillis) throws Exception {      //新建token 需要用户信息 比如用户名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;            // 以及一个long型数来表示在多久以后token会失效
        long nowMillis = System.currentTimeMillis();                                //使用HS256加密
        Date now = new Date(nowMillis);                                             //生成Token的时间
        Map<String,Object> claims = new HashMap<>();                                //创建payload的私有申明
        claims.put("username",userName);
        SecretKey key = generateKey();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)                          //现行设置自己的私有申明
                .setId("id")                                //设置唯一id 是JWT的唯一值 主要用来创建一个一次性的Token
                .setIssuedAt(now)                           //设定签发时间
                //.setSubject(subject)                      //用来设置一个JWT的主人 即它的所有人
                .signWith(signatureAlgorithm,key);          //设置签名使用的算法以及秘钥
        if(ttlMillis<=0) {
            ttlMillis = 1000*60*60;                         //如果传入的时间不为正整数 则设置token的有效时长为60分钟 即为默认值
        }
        Date exp = new Date(nowMillis+ttlMillis);
        builder.setExpiration(exp);
        return builder.compact();
    }

    public static SecretKey generateKey(){                         //生成秘钥key
        byte[] encodeKey = Base64.decodeBase64( TOKEN_KEY );
        SecretKey key = new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");//根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
        return key;
    }

    public static Claims parseJWT(String token) throws Exception {  //用来解析token
        SecretKey key = generateKey();                       //获取秘钥 需要和生成的秘钥一模一样
        Claims claims = Jwts.parser()                        //开始解析 得到defaultParser
                .setSigningKey(key)                          //设置解析的秘钥
                .parseClaimsJws(token).getBody();            //设置需要解析的JWT
        return claims;
    }

    public static boolean Verify(String token) throws Exception {        //验证token 形参为token本身
        Claims claims = parseJWT(token);                                 //验证方法 解析token 将解析得到的数据再生成新的token
        Date createdDate = claims.getIssuedAt();                          //比较旧的token和新的token是否相同
        Date expiredDate = claims.getExpiration();
        String userName = (String)claims.get("username");
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        Map<String,Object> testClaims = new HashMap<>();
        testClaims.put("username",userName);
        SecretKey key = generateKey();
        JwtBuilder builder = Jwts.builder()
                .setClaims(testClaims)
                .setId("id")
                .setIssuedAt(createdDate)
                .signWith(algorithm,key)
                .setExpiration(expiredDate);
        if(builder.compact().equals(token))
        {
            return true;
        }
        else {
            return false;
        }
    }

}
