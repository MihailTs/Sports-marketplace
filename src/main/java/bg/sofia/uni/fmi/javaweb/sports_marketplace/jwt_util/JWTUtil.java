package bg.sofia.uni.fmi.javaweb.sports_marketplace.jwt_util;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    private SecretKey key;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init(){
       this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+100*60*60*10))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean isTokenValid(String token, UserDetails user) {
        try {
            return extractEmail(token).equals(user.getUsername())&&!isExpired(token);
        }catch (JwtException e){
            return false;
        }
           /* try {
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
                return true;
            } catch (SecurityException e) {
                System.out.println("Invalid JWT signature: " + e.getMessage());
            } catch (MalformedJwtException e) {
                System.out.println("Invalid JWT token: " + e.getMessage());
            } catch (ExpiredJwtException e) {
                System.out.println("JWT token is expired: " + e.getMessage());
            } catch (UnsupportedJwtException e) {
                System.out.println("JWT token is unsupported: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("JWT claims string is empty: " + e.getMessage());
            }
            return false;
        }*/
    }



    public boolean isExpired(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }




}
