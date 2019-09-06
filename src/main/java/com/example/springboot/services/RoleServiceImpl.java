package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.Dao.IRoleDao;
import com.example.springboot.entities.Role;

@Service
public class RoleServiceImpl implements IRole {
	
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return (List<Role>) roleDao.findAll();
	}

}
