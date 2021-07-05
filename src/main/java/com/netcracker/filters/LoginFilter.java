package com.netcracker.filters;

import com.netcracker.entities.Entry;
import com.netcracker.repositories.EntryRepository;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: (?) не работает WebFilter
// @WebFilter(urlPatterns = {"/login"})
@Component
public class LoginFilter implements Filter {
    private EntryRepository entryRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain
    ) throws IOException, ServletException {
        String userId = request.getParameter("id");

        // если пользователь уже есть
        if (entryRepository.findByUserId(Integer.parseInt(userId)) != null) {
            filterChain.doFilter(request, response);
        } else {
            // записываем в бд
            // entryRepository.save(new Entry(userId, ???));
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.getWriter().write("unknown user with id=" + userId);

//        if(request.getSession().getAttribute("user") != null) {
//            filterChain.doFilter(request, response);
//        }

//        String myParam = request.getParameter("token");
//
//        if(!"blockTheRequest".equals(myParam)){
//            filterChain.doFilter(request, response);
//        }
    }

    @Override
    public void destroy() {
    }
}
