package kr.co.m_n09.interceptors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    private static final String HEADER_AUTH = "Auth-Token";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().startsWith("/admin")) {
            return true;
        }

        String token = request.getHeader(HEADER_AUTH);
        if (token != null && checkVerification(token)) {
            return true;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    private boolean checkVerification(String token) {
        try {
            Claims claims = Jwts.parser()
//                .setSigningKey(AdminController.KEY)
                    .parseClaimsJwt(token)
                    .getBody();

            return claims.getIssuer().equals("n09") &&
                    claims.getExpiration().getTime() > System.currentTimeMillis() &&
                    claims.getIssuedAt().getTime() < System.currentTimeMillis() &&
                    claims.getNotBefore().getTime() < System.currentTimeMillis();
        } catch (Exception e) {
            return false;
        }
    }
}
