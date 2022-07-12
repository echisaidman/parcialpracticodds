package dds.miliechi.parcialpractico.security;

import dds.miliechi.parcialpractico.entities.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 horas

    // TODO: sacar esto de Environment Variables
    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        SecretKey key = getSecretKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public String generateToken(AppUser appUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", appUser.getId().toString());
        return doGenerateToken(claims, appUser.getUsername());
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        SecretKey key = getSecretKey();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key/*, SignatureAlgorithm.HS512*/)
                .compact();
    }

    private SecretKey getSecretKey() {
        // String base64EncodedSecretKey = Base64.getEncoder().encodeToString(secret.getBytes());
        // byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        // SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return key;
    }

}
