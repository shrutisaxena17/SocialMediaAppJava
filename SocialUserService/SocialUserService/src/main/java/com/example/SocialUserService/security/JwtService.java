package com.example.SocialUserService.security;

import com.example.SocialUserService.dto.UsersDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {
    private static final String SECRET_KEY = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 86400000L;

    public String generateJwt(UsersDTO user) {
        return Jwts.builder()
                .setClaims(Map.of(
                        "email", user.getEmail(),
                        "roles", "ROLE_" + user.getRole()
                ))
                .setSubject(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public Claims parseJwt(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public void validateToken(String token) {
        parseJwt(token);
    }

    public String getUserIdFromToken(String token) {
        return parseJwt(token).getSubject();
    }

    public List<String> getRolesFromToken(String token) {
        return parseJwt(token).get("roles", List.class);
    }
}
