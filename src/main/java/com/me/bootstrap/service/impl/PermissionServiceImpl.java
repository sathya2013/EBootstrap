package com.me.bootstrap.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.bootstrap.dao.PermissionDao;
import com.me.bootstrap.entity.Permission;
import com.me.bootstrap.service.PermissionService;

@Service
@Transactional(readOnly=true)
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long>implements PermissionService{

	private PermissionDao permissionDao;
	@Autowired
	public PermissionServiceImpl(PermissionDao permissionDao) {
		super((JpaRepository<Permission, Long>) permissionDao);
		this.permissionDao = permissionDao;
	}

	@Override
	public List<Permission> findPermissionsByIds(Collection<Long> ids) {
		return permissionDao.findByIdIn(ids);
	}

	@Override
	public Permission loadOne(Long id) {
		
		return permissionDao.findOne(id);
	}
}
