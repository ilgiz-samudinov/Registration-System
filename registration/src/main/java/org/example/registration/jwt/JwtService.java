package org.example.registration.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {
    @Value("${application.security.jwt.secret.key}")
    private String secretKey;


    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh.token.expiration}")
    private long refreshExpiration;


    private Key signingKey;



    @PostConstruct
    private void init(){
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        signingKey = Keys.hmacShaKeyFor(keyBytes);
        log.info("JWT signing key successfully initialized");
    }

    private Key getSigningKey(){
        return signingKey;
    }

    public String getUserNameFromToken(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T>  T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails, jwtExpiration);
    }


    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, refreshExpiration);
    }


    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration){
        Date now = new Date();
        Date expry = new Date(now.getTime()+expiration);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Claims validateToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }











}
