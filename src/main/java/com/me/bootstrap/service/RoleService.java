/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.service.RoleService.java
 * Class:			RoleService
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.me.bootstrap.service;


import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.me.bootstrap.entity.Role;




/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:04:05 
 */

public interface RoleService extends BaseService<Role, Long>{
	
	//List<Role> find(Page page, String name);
	
	
	public Page<Role> findPage(Map<String, String[]> params, Pageable pageable);
}
