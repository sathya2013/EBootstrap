package com.me.bootstrap.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.me.bootstrap.entity.RolePermission;

public interface RolePermissionDao extends JpaRepository<RolePermission, Long>{

	
}
