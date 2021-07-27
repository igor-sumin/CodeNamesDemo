package com.netcracker.filters;

import com.netcracker.dto.RequestContext;
import com.netcracker.entities.UserToken;
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
    public static final String REQUEST_CONTEXT = "REQUEST_CONTEXT";
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
        String uri = httpRequest.getRequestURI();

        if (url.contains(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (uri.contains("/ws")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = httpRequest.getHeader("token");
        UserToken user = entryService.authorize(token);

        if (user != null) {
            httpRequest.setAttribute(REQUEST_CONTEXT, RequestContext.builder().userId(user.getUserId()).build());
            filterChain.doFilter(request, response);
            return;
        }

        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized error");
    }

    @Override
    public void destroy() {
    }
}
