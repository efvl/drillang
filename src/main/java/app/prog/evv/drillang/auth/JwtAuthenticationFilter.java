package app.prog.evv.drillang.auth;

import app.prog.evv.drillang.exception.auth.TokenExpiredException;
import app.prog.evv.drillang.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String username;
        if(authHeader == null || !authHeader.startsWith(BEARER)){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7); // token starts after the 'Bearer ' label
        username = jwtService.extractSubject(jwt);
        if(username == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userService.loadUserByUsername(username);
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken autToken = new UsernamePasswordAuthenticationToken(
                     userDetails, null, userDetails.getAuthorities()
                );
                autToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(autToken);
            }
            filterChain.doFilter(request, response);
        }
    }

}
