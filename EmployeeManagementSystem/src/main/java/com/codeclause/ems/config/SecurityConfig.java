/*
 * package com.codeclause.ems.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.AuthenticationProvider; import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.authentication.configuration.
 * AuthenticationConfiguration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.web.authentication.
 * UsernamePasswordAuthenticationFilter; import
 * org.springframework.web.servlet.handler.HandlerMappingIntrospector;
 * 
 * import lombok.AllArgsConstructor;
 * 
 * @AllArgsConstructor //@EnableWebSecurity //@Configuration public class
 * SecurityConfig {
 * 
 * private final CustomUserDetailsService customUserDetailsService; private
 * final JWTFilter jwtFilter;
 * 
 * //@Bean public SecurityFilterChain filterChain(HttpSecurity
 * http,HandlerMappingIntrospector introspector) throws Exception { http = http
 * .csrf(csrf -> csrf.disable()) .cors(cors -> cors.disable())
 * .authorizeHttpRequests(auth ->
 * auth.requestMatchers(HttpMethod.OPTIONS,"/ems/**").permitAll())
 * .authorizeHttpRequests(auth -> auth.requestMatchers
 * ("/ems/welcome","/ems/login","/ems/departments","/ems/roles",
 * "/ems/fetchYears","/ems/fetchMonths","/ems/fetchDays").permitAll().anyRequest
 * ().authenticated()) .sessionManagement(session ->
 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 * .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); http
 * = http.authenticationProvider(authProvider()); return http.build(); }
 * 
 * //@Bean public AuthenticationProvider authProvider() {
 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
 * provider.setUserDetailsService(customUserDetailsService);
 * provider.setPasswordEncoder(generatePasswordEncoder()); return provider; }
 * 
 * //@Bean public BCryptPasswordEncoder generatePasswordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * //@Bean public AuthenticationManager authManager(AuthenticationConfiguration
 * authConfig) throws Exception { return authConfig.getAuthenticationManager();
 * }
 * 
 * }
 */