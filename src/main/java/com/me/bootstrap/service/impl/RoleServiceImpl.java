/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.service.impl.RoleServiceImpl.java
 * Class:			RoleServiceImpl
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.me.bootstrap.service.impl;


import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.me.bootstrap.dao.RoleDao;
import com.me.bootstrap.entity.Role;
import com.me.bootstrap.service.RoleService;
import com.me.bootstrap.shiro.ShiroDbRealm;
import com.me.bootstrap.util.SearchFilter;




/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:04:52 
 */
@Service("roleService")
@Transactional(readOnly=true)
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {
	
	private RoleDao roleDao;
	
	@Autowired(required = false)
	private ShiroDbRealm shiroRealm;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		super((JpaRepository<Role, Long>) roleDao);
		this.roleDao = roleDao;
	}

	/**   
	 * @param role  
	 * @see com.ketayao.security.service.RoleService#update(com.ketayao.security.entity.main.Role)  
	 */
	@Transactional
	public void update(Role role) {
		roleDao.save(role);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param id  
	 * @see com.ketayao.security.service.RoleService#delete(java.lang.Long)  
	 */
	@Transactional
	public void delete(Long id) {
		roleDao.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Override
	public org.springframework.data.domain.Page<Role> findPage(
			Map<String, String[]> params, Pageable pageable) {
		
		return roleDao.findAll(spec(params),pageable);
	}
	
	private Specification<Role> spec(Map<String, String[]> params) {
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		Specification<Role> sp = SearchFilter.spec(filters, Role.class);
		return sp;
	}

}
