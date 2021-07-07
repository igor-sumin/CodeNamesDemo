package com.netcracker.filters;

import com.netcracker.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: learn CORS

@Component
public class EntryFilter implements Filter {
    private final AuthService authService;
    private static final Logger logger = Logger.getLogger(EntryFilter.class.getName());

    @Autowired
    public EntryFilter(AuthService authService) {
        this.authService = authService;
    }

    // TODO:
    private final String[] url = new String[] {"/login", "/register"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        if (Arrays.stream(url).anyMatch(u -> u.equals(httpRequest.getRequestURI()))) {
            filterChain.doFilter(request, response);
        }

        String token = httpRequest.getHeader("token");

        // TODO: потом убери
        // filterChain.doFilter(request, response);

        if (authService.authorize(token)) {
            filterChain.doFilter(request, response);
        }

        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized error");
    }

    @Override
    public void destroy() {
    }
}
