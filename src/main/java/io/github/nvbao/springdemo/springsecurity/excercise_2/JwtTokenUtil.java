package io.github.nvbao.springdemo.springsecurity.excercise_2;


import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final String SECRET_KEY = "01234567890123456789012345678901"; // 32-byte secret key
    private static final long EXPIRATION_TIME = 3600000; // Set time for token 1 hour

    /**
     * Create JWT token
     */
    public String generateToken(String username, String role) throws Exception {
        JWSSigner signer = new MACSigner(SECRET_KEY);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .claim("role", role)
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    /**
     * Check token
     */
    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.verify(new MACVerifier(SECRET_KEY))
                    && new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get username from token
     */
    public String getUsernameFromToken(String token) throws ParseException {
        return SignedJWT.parse(token).getJWTClaimsSet().getSubject();
    }

    /**
     * Get role from token
     */
    public String getRoleFromToken(String token) throws ParseException {
        return SignedJWT.parse(token).getJWTClaimsSet().getStringClaim("role");
    }
}

