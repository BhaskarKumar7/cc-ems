package com.codeclause.ems.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

//import com.codeclause.ems.config.JWTUtil;
import com.codeclause.ems.dto.LoginCredsDto;
import com.codeclause.ems.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
	
	/*
	 * @Autowired private AuthenticationManager authenticationManager;
	 * 
	 * @Autowired private JWTUtil jwtUtil;
	 * 
	 * @Override public Map<String, String> loginUser(LoginCredsDto credsDto) {
	 * Map<String,String> responseMap = null; UsernamePasswordAuthenticationToken
	 * authenticationToken = new
	 * UsernamePasswordAuthenticationToken(credsDto.getEmailId(),
	 * credsDto.getPassword()); Authentication securityPrincipal =
	 * authenticationManager.authenticate(authenticationToken);
	 * if(securityPrincipal.isAuthenticated()) { responseMap = new HashMap<>();
	 * responseMap.put("token", jwtUtil.generateToken(credsDto.getEmailId()));
	 * responseMap.put("adminName",securityPrincipal.getName()); } return
	 * responseMap; }
	 */

}
