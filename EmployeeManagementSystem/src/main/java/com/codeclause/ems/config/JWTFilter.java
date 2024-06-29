/*
 * package com.codeclause.ems.config;
 * 
 * import java.io.IOException;
 * 
 * import org.springframework.http.HttpStatus; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.web.authentication.
 * WebAuthenticationDetailsSource; import
 * org.springframework.stereotype.Component; import
 * org.springframework.util.StringUtils; import
 * org.springframework.web.ErrorResponse; import
 * org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * import io.jsonwebtoken.ExpiredJwtException; import
 * jakarta.servlet.FilterChain; import jakarta.servlet.ServletException; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse; import lombok.AllArgsConstructor;
 * import lombok.extern.slf4j.Slf4j;
 * 
 * @Slf4j //@Component
 * 
 * @AllArgsConstructor public class JWTFilter extends OncePerRequestFilter {
 * 
 * private final JWTUtil jwtUtil; private final CustomUserDetailsService
 * customUserDetailsService;
 * 
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException {
 * log.info("-------------jwt filter intercepted the request---------------");
 * request.getHeaderNames().asIterator().forEachRemaining(itr -> {
 * log.info("header---> {} || value----> {}", itr, request.getHeader(itr)); });
 * final String header = request.getHeader("Authorization");
 * log.info("authorization header---> {}", header); if
 * (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
 * log.info("--- header is empty ----"); filterChain.doFilter(request,
 * response); return; }
 * 
 * try { log.info("****&7887887&*&*&*&*&*&*&*&*"); String token =
 * header.split(" ")[1].trim(); String email = jwtUtil.extractUsername(token);
 * log.info("extracted email from the token----> {}", email); UserDetails
 * userDetails = customUserDetailsService.loadUserByUsername(email); if
 * (jwtUtil.isTokenValid(token, userDetails)) {
 * log.info("===json web token is valid===");
 * UsernamePasswordAuthenticationToken unPwdAuthTkn = new
 * UsernamePasswordAuthenticationToken(userDetails, null,
 * userDetails.getAuthorities()); unPwdAuthTkn.setDetails(new
 * WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(unPwdAuthTkn);
 * filterChain.doFilter(request, response); } } catch (ExpiredJwtException eje)
 * { log.error("token expired---> {}",eje); ErrorResponse errorResponse =
 * ErrorResponse .builder(eje, HttpStatus.BAD_REQUEST,
 * "Your session exired. please login again!").build();
 * response.setContentType("application/json");
 * response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
 * response.getWriter().write(new
 * ObjectMapper().writeValueAsString(errorResponse));
 * response.getWriter().flush(); } catch (Exception e) { ErrorResponse
 * errorResponse = ErrorResponse .builder(e, HttpStatus.INTERNAL_SERVER_ERROR,
 * "Not able to fulfill your request!").build();
 * response.setContentType("application/json");
 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 * response.getWriter().write(new
 * ObjectMapper().writeValueAsString(errorResponse));
 * response.getWriter().flush(); } } }
 */