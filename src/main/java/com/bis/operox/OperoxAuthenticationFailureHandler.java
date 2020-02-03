package com.bis.operox;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * Custom AuthenticationFailureHandler for customizing error messages
 * 
 * @author: Naresh Bolisetty
 */
@Configuration
public class OperoxAuthenticationFailureHandler implements AuthenticationFailureHandler{
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		  request.setAttribute("username", request.getParameter("username"));
		  request.setAttribute("password", request.getParameter("password"));
		  if(exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			request.setAttribute(exception.getMessage(),true);
		   } 
			ServletContext context = request.getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/operox/login");
			rd.forward(request, response);
		
	}

}