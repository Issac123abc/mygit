package cn.com.nexwise.all.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {

	private static JwtUtils jwtUtils;

	@PostConstruct
	private void init() {
		JwtUtils.jwtUtils = this;
	}

    /**
     * 密钥
     */
    private static final String SECRET = "nexwise";

    public static final String USER_NO = "username";
    public static final String ISSUER="dzwl.nexwise.com";

    @Value("${jwt.expire.time}")
    public String expireTime;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).withClaim(USER_NO, username).build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 生成签名，指定时间后过期，一经生成不可修改，令牌在指定时间内一直有效
     * @param username 用户编号
     * @return 加密的token
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + Long.parseLong(JwtUtils.jwtUtils.expireTime) * 1000);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create().withClaim(USER_NO, username).withIssuer(ISSUER).withExpiresAt(date).sign(algorithm);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得token中的信息,无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(USER_NO).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 解释token，返回账号及过期时间
     *
     * */
    public static String parseToken(String token) {
    	DecodedJWT decodedJWT = JWT.decode(token);
    	String userName = decodedJWT.getClaim(USER_NO).asString();
    	Date expiresAt = decodedJWT.getExpiresAt();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String format = sdf.format(expiresAt);
    	StringBuilder builder = new StringBuilder();
    	builder.append("账号:").append(userName).append(",过期时间:").append(format);
    	return builder.toString();
    }

}
