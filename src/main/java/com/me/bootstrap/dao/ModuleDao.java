/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.dao.ModuleDao.java
 * Class:			ModuleDao
 * Date:			2012-8-6
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.me.bootstrap.entity.Module;



/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-6 上午9:31:03 
 */

public interface ModuleDao extends JpaRepository<Module, Long> {
			
	Page<Module> findByParentId(Long parentId, Pageable pageable);
	
	List<Module> findByParentId(Long parentId);
	
	Page<Module> findByParentAndNameContaining(Long parentId, String name, Pageable pageable);
	
//	@QueryHints(value={
//			@QueryHint(name="org.hibernate.cacheable",value="true"),
//			@QueryHint(name="org.hibernate.cacheRegion",value="com.me.bootstrap.entity")
//		}
//	)
	@Query("from Module m order by m.priority ASC")
	List<Module> findAllWithCache();
	
	List<Module> findByParentIsNull();
}
