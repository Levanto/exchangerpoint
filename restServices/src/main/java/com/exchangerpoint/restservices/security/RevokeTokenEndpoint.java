package com.exchangerpoint.restservices.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@FrameworkEndpoint
public class RevokeTokenEndpoint {
 
    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;
 
    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
 
    public void revokeToken(HttpServletRequest request) {
    	
       
        String tokenId = request.getParameter("access_token");
        String authorization = request.getHeader("Authorization");
        
        
        if (tokenId != null ){
        	tokenServices.revokeToken(tokenId);
        }
        
        if (authorization != null && authorization.contains("Bearer")){
            String token = authorization.substring("Bearer".length()+1);
            
            tokenServices.revokeToken(token);
        }
        
    }
}