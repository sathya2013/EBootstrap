package com.me.bootstrap.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.me.bootstrap.entity.Permission;

public interface PermissionDao extends JpaRepository<Permission, Long>{

	public List<Permission> findByIdIn(Collection<Long> ids);
}
