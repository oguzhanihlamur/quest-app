package dev.antozy.questapp.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${questapp.secret}")
    private String APP_SECRET;
    @Value("${questapp.expires.in}")
    private Long EXPIRES_IN;

    public String generateJwtToken(Authentication authentication) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder()
                .setSubject(jwtUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }

    public String generateJwtTokenByUserName(String userName) {
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(userName)
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }

    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(APP_SECRET)
                    .parseClaimsJws(token);

            return !isTokenExpired(token);
        } catch (SignatureException | IllegalArgumentException | UnsupportedJwtException | MalformedJwtException |
                 ExpiredJwtException signatureException) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts
                .parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
