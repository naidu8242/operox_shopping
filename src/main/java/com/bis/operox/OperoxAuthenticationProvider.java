package com.bis.operox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.bis.operox.inv.dao.entity.User;
import com.bis.operox.inv.service.UserService;

/**
 * Custom AuthenticationProvider 
 * 
 * @author: Naresh Bolisetty
 */
@Component
public class OperoxAuthenticationProvider  implements AuthenticationProvider {
 
    @Autowired
    private UserService userService;
    
    @Autowired
    private OperoxPasswordEncoder operoxPasswordEncoder;
  
    /*@param
     * 
     * Overriding AuthenticationProvider authenticate(-) method to handle NullPointer Exception for unregistered users
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
          String username = authentication.getName();
          String password = (String) authentication.getCredentials();
     
          User  user = userService.findByUserName(username);
          if(user == null){
        	  throw new BadCredentialsException("login_unRegistered_error");
          }
          else{
        	  if(operoxPasswordEncoder.matches(password, user.getPassword())){
  		      List<GrantedAuthority> authorities = buildUserAuthority(user.getRole().getRoleName()); 
     		  return new UsernamePasswordAuthenticationToken(username, password, authorities);
        	  }
        	  else{
        		  throw new BadCredentialsException("login_password_error");
        	  }
          }
    }
 
    @Override
	public boolean supports(Class<?> arg0) {
        return true;
    }

    private List<GrantedAuthority> buildUserAuthority(String userRole) {

    	Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
       
    	setAuths.add(new SimpleGrantedAuthority(userRole));
    	
    	List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
    	return Result;
    }



	


}
