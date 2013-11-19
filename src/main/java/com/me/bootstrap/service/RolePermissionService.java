package com.me.bootstrap.service;

import java.util.Collection;

import com.me.bootstrap.entity.RolePermission;

public interface RolePermissionService extends BaseService<RolePermission, Long>{

	public void saveAll(Collection<RolePermission> rolePermissions);
}
