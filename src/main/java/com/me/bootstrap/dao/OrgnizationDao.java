/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.dao.OrganizationDao.java
 * Class:			OrganizationDao
 * Date:			2012-8-27
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.me.bootstrap.dao;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.me.bootstrap.entity.Orgnization;



/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-27 下午3:55:47 
 */

public interface OrgnizationDao extends JpaRepository<Orgnization, Long>{
	Page<Orgnization> findByParentId(Long parentId, Pageable pageable);
	
	Page<Orgnization> findByParentIdAndNameContaining(Long parentId, String name, Pageable pageable);
//	@QueryHints(value={
//			@QueryHint(name="org.hibernate.cacheable",value="true"),
//			@QueryHint(name="org.hibernate.cacheRegion",value="com.me.bootstrap.entity")
//		}
//	)
	@Query("from Orgnization")
	List<Orgnization> findAllWithCache();
	
	//查找部门列表
	public Page<Orgnization> findAll(Specification<Orgnization> spec, Pageable pageable);
}
