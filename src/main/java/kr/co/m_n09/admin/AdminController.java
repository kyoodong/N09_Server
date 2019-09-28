package kr.co.m_n09.admin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //n09app!admin
    @GetMapping("login")
    public ResponseEntity<String> login(@RequestParam String password) throws Exception {
        int count = adminService.login(password);
        if (count > 0) {
            // 30분
            Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 30);
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("www.n09.cafe24.com/jwt_claims/is_admin", true);

            String token = Jwts.builder()
                    .setExpiration(expiration)
                    .setIssuer("n09")
                    .setNotBefore(new Date(System.currentTimeMillis()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .addClaims(claims)
//                    .signWith(KEY)
                    .compact();

            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}
