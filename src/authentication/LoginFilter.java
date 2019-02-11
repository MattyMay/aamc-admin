/*
 * 
 */
package authentication;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Catches requests to secured JSPs and checks that a session
 * exists with a logged in user.
 * 
 * Full disclosure: Largely copied from a stack overflow answer.
 */
public class LoginFilter implements Filter {

    HttpServletRequest request = null;
    HttpServletResponse response = null;
    HttpSession session = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.request = (HttpServletRequest) request;
        this.response = (HttpServletResponse) response;
        this.session = this.request.getSession(false);
        String contextPath = this.request.getContextPath();
        String loginURI = contextPath + "/login.jsp";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = this.request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(this.request, this.response);
        } else {
            this.response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }

}
