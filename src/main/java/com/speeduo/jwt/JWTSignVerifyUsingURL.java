package com.speeduo.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

public class JWTSignVerifyUsingURL {
    private static final Log log = LogFactory.getLog(JWTSignVerifyUsingURL.class);
    //replace this with your jwt token
    private static String  jwtToken = "eyJhbGciOiJSUzI1NiIsIng1dCI6Ik5tSm1PR1V4TXpabFlqTTJaRFJoTlRabFlUQTFZemRoWlRSaU9XRTBOV0kyTTJKbU9UYzFaQSJ9.eyJodHRwOlwvXC93c28yLm9yZ1wvZ2F0ZXdheVwvYXBwbGljYXRpb25uYW1lIjoiT2F1dGg3IiwiZXhwIjoxNDUyNTk0ODkyLCJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJodHRwOlwvXC93c28yLm9yZ1wvZ2F0ZXdheVwvc3Vic2NyaWJlciI6ImFkbWluQGNhcmJvbi5zdXBlciIsImlzcyI6Imh0dHA6XC9cL3dzbzIub3JnXC9nYXRld2F5IiwiaHR0cDpcL1wvd3NvMi5vcmdcL2dhdGV3YXlcL2VuZHVzZXIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJodHRwOlwvXC93c28yLm9yZ1wvY2xhaW1zXC9yb2xlIjoiYWRtaW4sQXBwbGljYXRpb25cL2Rld3ZkZXcsQXBwbGljYXRpb25cL09hdXRoNyxJbnRlcm5hbFwvZXZlcnlvbmUiLCJodHRwOlwvXC93c28yLm9yZ1wvY2xhaW1zXC9lbWFpbGFkZHJlc3MiOiJhZG1pbkB3c28yLmNvbSIsImlhdCI6MTQ1MjU5MzI1NCwiaHR0cDpcL1wvd3NvMi5vcmdcL2NsYWltc1wvb3JnYW5pemF0aW9uIjoiV1NPMiJ9.WRo2p92f-pt1vH9xfLgmrPWNKJfmST2QSPYcth7gXKz64LdP9zAMUtfAk9DVRdHTIQR3gX0jF4Ohb4UbNN4Oo97a35oTL1iRxIRTKUkh8L1dpt3H03Z0Ze7Q2giHGZikMIQv3gavHRYKjNMoU_1MuB90jiK7";

    public static void main(String[] args) throws  IOException, JwkException {
        PublicKey publicKey = getPublicKeyFromURL();
        Claims claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwtToken).getBody();
        if(log.isDebugEnabled()){
            log.debug("Claim Properties");
            log.debug("Issuer: " + claims.getIssuer());
            log.debug("Expiration: " + claims.getExpiration());
        }
    }

    public static RSAPublicKey getPublicKeyFromURL() throws JwkException, MalformedURLException {
        //replace JWK_URL with actual URL
        JwkProvider provider = new UrlJwkProvider(new URL("JWK_URL"));
        //put kid parameter of JWK here
        Jwk jwk = provider.get("PUBLIC_KEY_ID");
        RSAPublicKey publicKey =  (RSAPublicKey)jwk.getPublicKey();
        return publicKey;
    }
}
