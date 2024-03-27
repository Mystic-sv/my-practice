package com.techymeet.mytodo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techymeet.mytodo.bo.EmployeeBO;
import com.techymeet.mytodo.bo.LoginBO;
import com.techymeet.mytodo.bo.LoginResponse;
import com.techymeet.mytodo.securityconfig.JwtTokenUtils;
import com.techymeet.mytodo.service.UserDetailsImpl;
import com.techymeet.mytodo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/v1/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginBO login,HttpServletRequest request) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new LoginResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles));
	}
	
	
	
	@PostMapping("/v1/register")
	public ResponseEntity<EmployeeBO> register(@RequestBody EmployeeBO employee){
		try {
		
			employee=userService.registerEmployee(employee);
			if(null==employee) {
				return new ResponseEntity<EmployeeBO>(employee, HttpStatus.EXPECTATION_FAILED);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return new ResponseEntity<EmployeeBO>(employee, HttpStatus.OK);	
	}
	
	@GetMapping("/v1/findmail/{email}")
	public ResponseEntity<Boolean> findByEmail(@PathVariable String email){
		boolean status=false;
		try {
			
			status=userService.findByEmail(email);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		
		
	}
}
