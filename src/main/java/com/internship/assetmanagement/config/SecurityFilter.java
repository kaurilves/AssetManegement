package com.internship.assetmanagement.config;

import com.internship.assetmanagement.services.user.UserService;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class SecurityFilter implements Filter {

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String authToken = req.getHeader("authorization").replace("Bearer ", "");

        try {
            var user = userService.getUser(authToken);

            if (user == null) {
                throw new Exception("Unauth");
            }

            req.setAttribute("user", user);
        } catch (Exception ignored) {
            res.sendError(401, "Unauthorized access");
            return;
        }

        filterChain.doFilter(req, res);
    }
}
