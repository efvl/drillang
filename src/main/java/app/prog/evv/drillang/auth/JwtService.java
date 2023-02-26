package app.prog.evv.drillang.auth;

import app.prog.evv.drillang.exception.auth.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

    @Value("${auth.secret-key:12345}")
    private String SECRET_KEY;

//    @Value("#{${auth.access-token.expirationMinutes:15} * 60 * 1000}")
    @Value("#{${auth.access-token.expirationMinutes:15} * 1000}")
    private long ACCESS_TOKEN_EXP;

//    @Value("#{${auth.refresh-token.expirationHours:24} * 60 * 60 * 1000}")
    @Value("#{${auth.refresh-token.expirationHours:24} * 60 * 1000}")
    private long REFRESH_TOKEN_EXP;

    public String generateAccessToken(UserDetails userDetails){
        return generateAccessToken(new HashMap<>(), userDetails);
    }

    public String generateAccessToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXP))
                .signWith(getSingninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXP))
                .signWith(getSingninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parser()
                .setSigningKey(getSingninKey())
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSingninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractSubject(token);
        return userDetails.getUsername() != null && userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractSubject(String jwtToken) {
        String subject = null;
        try {
            subject = extractClaim(jwtToken, Claims::getSubject);
        } catch(ExpiredJwtException ex){
            DefaultClaims claims = (DefaultClaims) ex.getClaims();
            LOGGER.error(String.format("get subject (%s) from expired token ", claims.getSubject()), ex.fillInStackTrace());
        }
        return subject;
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }


}
