package com.example.proxy.security;

import com.example.proxy.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class AuthHandler {
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService customUserDetailsService;


    public ResponseEntity<?> createToken(LoginRequestDto user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Error", e);
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok().body(new LoginResponseDto(token));
    }

    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
        // From the HttpRequest get the claims
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

}
