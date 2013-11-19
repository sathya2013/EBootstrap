package com.me.bootstrap.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.bootstrap.dao.RolePermissionDao;

import com.me.bootstrap.entity.RolePermission;
import com.me.bootstrap.service.RolePermissionService;
@Service
@Transactional(readOnly=true)
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, Long> implements RolePermissionService{

	private RolePermissionDao rolePermissionDao;
	
	@Autowired
	public RolePermissionServiceImpl(RolePermissionDao rolePermissionDao) {
		super((JpaRepository<RolePermission, Long>) rolePermissionDao);
		this.rolePermissionDao =rolePermissionDao;
	}

	@Override
	public void saveAll(Collection<RolePermission> rolePermissions) {
		
		rolePermissionDao.save(rolePermissions);
	}

}
