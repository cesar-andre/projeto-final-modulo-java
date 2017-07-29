package br.com.simpleblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.simpleblog.controller.LoginController;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LoginController loginController = (LoginController)((HttpServletRequest)request).getSession().getAttribute("loginController");
        
	    
        if (loginController == null || !loginController.isLogado() ) {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
        }
         
        chain.doFilter(request, response);
		
	}

}
