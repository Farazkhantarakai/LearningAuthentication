package learnJwt.app.Services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {


    private final long  VALIDITY= TimeUnit.MINUTES.toMillis(30);


public String generateToken(UserDetails userDetails){
Map<String, String> claims = new HashMap<>();
//claims.put("iss","www.google.com");
return Jwts.builder().claims(claims).subject(userDetails.getUsername())
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
        .signWith(generateKey())
        .compact();
}


private SecretKey generateKey(){
    String SECRET = "729D10648439D5E2FC719237EB70047EBE3A150B209AD31AC2AB8F6DCA40284CD7FAB039A62D69AC99F62E03DD8F159693F05B4AA61A854629BB84962AE0C101";
    byte[] decodeKey = Base64.getDecoder().decode(SECRET)   ;
    return Keys.hmacShaKeyFor(decodeKey);
}


    public String extractUserName(String jwt) {
    Claims claims= getClaim(jwt);


return claims.getSubject();
}


    private Claims getClaim(String jwt){

    return Jwts.parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(jwt)
            .getPayload();
    }

    public boolean isTokenValid(String jwt) {

    Claims claims=getClaim(jwt);

  return   claims.getExpiration().after(Date.from(Instant.now()));

    }
}
