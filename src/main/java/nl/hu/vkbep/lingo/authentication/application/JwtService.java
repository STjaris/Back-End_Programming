package nl.hu.vkbep.lingo.authentication.application;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private String key = "key";

    public String generateToken(UserDetails userDetails) {
        Map map = new HashMap<>();
        return createToken(map, userDetails.getUsername());
    }

    public String createToken(Map map, String subject) {
        return Jwts.builder().setClaims(map).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }


}
