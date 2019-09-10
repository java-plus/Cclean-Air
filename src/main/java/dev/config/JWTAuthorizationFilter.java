package dev.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author Cécile Peyras
 * Classe filter qui gère les cookies
 */
@Configuration
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {



        // Recherche du jeton par Cookie
        if(request.getCookies() != null) {
            Stream.of(request.getCookies()).filter(cookie -> cookie.getName().equals(TOKEN_COOKIE))
                    .map(cookie -> cookie.getValue())
                    .forEach(token -> {
                        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

                        String userName = body.getSubject();

                        List<String> roles = body.get("roles", List.class);

                        List<SimpleGrantedAuthority> authorities = roles
                                .stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());

                        Authentication  authentication  = new UsernamePasswordAuthenticationToken(userName, null, authorities);

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });
        }


        filterChain.doFilter(request, response);
    }
}


