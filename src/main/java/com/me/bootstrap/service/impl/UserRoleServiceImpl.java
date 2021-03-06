/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.service.impl.UserRoleServiceImpl.java
 * Class:			UserRoleServiceImpl
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.me.bootstrap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.bootstrap.dao.UserRoleDao;
import com.me.bootstrap.entity.UserRole;
import com.me.bootstrap.service.UserRoleService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:09:50 
 */
@Service("userRoleService")
@Transactional(readOnly=true)
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, Long> implements UserRoleService {

	private UserRoleDao userRoleDao;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public UserRoleServiceImpl(UserRoleDao userRoleDao) {
		super((JpaRepository<UserRole, Long>) userRoleDao);
		this.userRoleDao = userRoleDao;
	}

	/**   
	 * @param userId
	 * @return  
	 * @see com.ketayao.security.service.UserRoleService#find(Long)  
	 */
	public List<UserRole> find(Long userId) {
		return userRoleDao.findByUserId(userId);
	}
	
    @Transactional
	@Override
	public void deleteUserRole(Long userId) {
		
		List<UserRole> userRoles = userRoleDao.findByUserId(userId);
		for(UserRole ur:userRoles)
		{
			userRoleDao.delete(ur.getId());
		}
		
	}

}
