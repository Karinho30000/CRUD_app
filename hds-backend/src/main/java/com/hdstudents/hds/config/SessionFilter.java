package com.hdstudents.hds.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class SessionFilter extends OncePerRequestFilter {
    private static final Logger logger = Logger.getLogger(SessionFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");

        // 🛑 Allow preflight requests through immediately
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        String path = request.getRequestURI();
        logger.info(path);
        if(path.startsWith("/api/auth")){
            logger.info("Allowing public access");
            filterChain.doFilter(request, response);
            return;
        }

        Boolean authenticated = (Boolean) request.getSession().getAttribute("authenticated");
        logger.info("Authenticated status: " + authenticated);
        if(authenticated != null && authenticated){
            String sessionId = request.getSession().getId();
            response.setHeader("Set-Cookie", "JSESSIONID=" + sessionId + "; Path=/; HttpOnly; SameSite=None; Secure");
            filterChain.doFilter(request, response);
        } else{
            logger.warning("Unauthorized access to " + path);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("You are not authenticated");
        }
    }
}
