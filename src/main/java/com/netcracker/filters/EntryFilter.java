package com.netcracker.filters;

import com.netcracker.services.entry.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Order(2)
public class EntryFilter implements Filter {
    private final EntryService entryService;
    private static final List<String> url = Arrays.asList("/login", "/register");

    @Autowired
    public EntryFilter(EntryService authService) {
        this.entryService = authService;
    }

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

        if (url.contains(httpRequest.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = httpRequest.getHeader("token");
        if (entryService.authorize(token)) {
            filterChain.doFilter(request, response);
        }

        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized error");
    }

    @Override
    public void destroy() {
    }
}
