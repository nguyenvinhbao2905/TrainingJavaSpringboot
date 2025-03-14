package io.github.nvbao.springdemo.springsecurity.excercise_4.config;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "0123456789abcdef0123456789abcdef"; // 32-byte secret key
    private static final long EXPIRATION_TIME = 3600000; // Set time for token 1 hour

    public static String generateToken(String username, String role) throws JOSEException {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .claim("role", role)
                .expirationTime(new Date(new Date().getTime() + EXPIRATION_TIME))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claimsSet
        );

        signedJWT.sign(new MACSigner(SECRET));
        return signedJWT.serialize();
    }

    public static boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET);
            return signedJWT.verify(verifier) &&
                    new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime());
        } catch (Exception e) {
            return false;
        }
    }
}
