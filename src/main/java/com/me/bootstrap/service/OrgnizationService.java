/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.service.OrganizationService.java
 * Class:			OrganizationService
 * Date:			2012-8-27
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.me.bootstrap.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import com.me.bootstrap.entity.Orgnization;
import com.me.bootstrap.util.Page;


/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-27 下午3:56:25 
 */

public interface OrgnizationService extends BaseService<Orgnization, Long>{
	
	List<Orgnization> find(Long parentId, Page page);
	
	List<Orgnization> find(Long parentId, String name, Page page);
	
	Orgnization getTree();
	
	public org.springframework.data.domain.Page<Orgnization> findPage(Map<String, String[]> params, Pageable pageable);
}
