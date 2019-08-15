package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ins.pos.entity.Role;
import com.ins.pos.entity.User;
import com.ins.pos.entity.UserRoles;
import com.ins.pos.repository.RoleRepository;
import com.ins.pos.repository.UserRepository;
import com.ins.pos.repository.UserRolesRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	public User findUserByEmail(String email) {
	    return userRepository.findByEmail(email);
	}
	
	public void saveUser(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setEnabled(true);
	    userRepository.save(user);
	    Role userRole = roleRepository.findByRoleName("Online Customer");
	    UserRoles userRoles = new UserRoles();
	    userRoles.setActive(true);
	    userRoles.setUser(user);
	    userRoles.setRole(userRole);
	    userRolesRepository.save(userRoles);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    User user = userRepository.findByEmail(email);
	    List<UserRoles> userRoleList = userRolesRepository.findByUserAndActive(user, true);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(userRoleList);
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	private List<GrantedAuthority> getUserAuthority(List<UserRoles> userRoleList) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoleList.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole().getRoleName()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
}