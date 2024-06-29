/*
 * package com.codeclause.ems.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.codeclause.ems.entity.SuperAdminEntity; import
 * com.codeclause.ems.repository.SuperAdminRepository;
 * 
 * @Service public class CustomUserDetailsService implements UserDetailsService
 * {
 * 
 * @Autowired private SuperAdminRepository superAdminRepo;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { SuperAdminEntity superAdminEntity =
 * superAdminRepo.findByEmail(username); if(superAdminEntity == null) { throw
 * new UsernameNotFoundException("No user found with the email id"); } return
 * new CustomUserDetails(superAdminEntity.getEmail(),
 * superAdminEntity.getPassword()); }
 * 
 * }
 */