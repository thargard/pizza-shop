package modsen.pizza.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET = "d06a0bcd92c1202785692161331a889237682aa1e91e86a4fa753e94525b12c1ea40e02262fe4e7b7dd8c2b3cc1610cf65883c159a8df68397d1f73208853861b919519876f4be4c9e6442188b6d03c81aae0710e948565f0ef0bd73b93349eb24914de52bbbde6d79c2b659a994e582f0eb80fb955cc0e3263c8aa20eacfcf6837070b8c808e616152859c69490b0a7d8181c35223c7197c2452ca13edbc49dee586f9c2640fc53aaa7c83f4ca04c1a30ba8d6030381ef472d73d1e18d6f86c4b5c35326f5954603d369ccd0635bb828370c55fa467e8d10013b561f1dbc12229d102a98444369ce3664c8a6a2f513069d6681362bbd8d622c1943157bbc4cc";

    public void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
