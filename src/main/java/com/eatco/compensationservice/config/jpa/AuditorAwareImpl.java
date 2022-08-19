package com.eatco.compensationservice.config.jpa;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.eatco.compensationservice.config.authentication.JwtTokenService;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private JwtTokenService jwtService;

	@Autowired
	public AuditorAwareImpl(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public AuditorAwareImpl() {
	}

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return Optional.empty();
		}
		String token = (String) authentication.getCredentials();
		if (token != null && !token.isEmpty()) {
			String userId = jwtService.getUserData(token);
			return Optional.ofNullable(userId);
		}
		return Optional.ofNullable("admin");
	}

}

