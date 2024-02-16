package com.example.SpringJwtAuthentication.security.jwt;

import com.example.SpringJwtAuthentication.model.User;
import com.example.SpringJwtAuthentication.security.userprincal.UserPrinciple;
import com.example.SpringJwtAuthentication.service.UserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Autowired
    UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "SecretKey";
    private int jwtExpiration = 86400;

    // hàm để tạo token
    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000)) //xét thời gian sống token
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // check token có hợp lệ hay không
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) { // khoong đúng định dạng
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) { // quá hạn thời gian sống
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) { // không hỗ trợ token đó
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) { //có ký tự trống không hợp lệ
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;

    }
}



