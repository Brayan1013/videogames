package com.example.springboot.Auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;


import com.example.springboot.entities.User;
import com.example.springboot.services.IUser;

@Component
public class InfoAuthToken implements TokenEnhancer {
	
	@Autowired
	private IUser userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = userService.findByUsername(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		info.put("Nombre", user.getNombre());
		info.put("Apellido", user.getApellido());
		info.put("Email", user.getEmail());
		info.put("Foto", user.getFoto());
		info.put("Id", user.getId());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
