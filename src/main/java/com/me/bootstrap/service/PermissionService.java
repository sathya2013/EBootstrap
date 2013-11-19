package com.me.bootstrap.service;


import java.util.Collection;
import java.util.List;

import com.me.bootstrap.entity.Permission;



public interface PermissionService  extends BaseService<Permission, Long>{

	List<Permission> findPermissionsByIds(Collection<Long> ids);
	Permission loadOne(Long id);
}
