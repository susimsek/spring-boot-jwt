package com.jwt.springbootjwt.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Kimliği doğrulanmamış her isteği reddeder ve 401 hata kodunu gönderir
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	//Spring default 403 dönüyor hatada.biz 401 döndük
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}