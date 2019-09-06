package com.example.springboot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.Dao.IUserDao;
import com.example.springboot.entities.User;

@Service
public class UserService implements IUser, UserDetailsService {
	
	@Autowired
	private IUserDao userDao;
	
	private Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		
		if(user == null) {
			log.error("Ocurrio un error");
			throw new UsernameNotFoundException("No se ha encontrado al usuario: " + username);
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
		
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.isEnable(), true, true, true, authorities);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Transactional(readOnly=true)
	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

}
