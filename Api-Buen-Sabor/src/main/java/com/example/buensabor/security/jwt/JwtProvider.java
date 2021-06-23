package com.example.buensabor.security.jwt;

import com.example.buensabor.security.dto.JwtDto;
import com.example.buensabor.security.entities.UsuarioPrincipal;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generarToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        List<String> roles = usuarioPrincipal
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return Jwts.builder().setSubject(usuarioPrincipal.getEmail())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 180))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacío");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (SignatureException e) {
            logger.error("fallo en la firma");
        }
        return false;
    }

    public String refreshToken(JwtDto jwtDto) throws ParseException {
        JWT jwt = JWTParser.parse(jwtDto.getToken());
        JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
        String email = claimsSet.getSubject();
        List<String> roles = (List<String>) claimsSet.getClaim("roles");
        return Jwts.builder().setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
}
