/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.service.impl.ModuleServiceImpl.java
 * Class:			ModuleServiceImpl
 * Date:			2012-8-6
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.me.bootstrap.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.bootstrap.dao.ModuleDao;
import com.me.bootstrap.entity.Module;
import com.me.bootstrap.exception.ServiceException;
import com.me.bootstrap.service.ModuleService;
import com.me.bootstrap.util.Page;
import com.me.bootstrap.util.PageUtils;


/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-6 上午9:45:28 
 */
@Service("moduleService")
@Transactional(readOnly=true)
public class ModuleServiceImpl extends BaseServiceImpl<Module, Long>implements ModuleService {
	
	private ModuleDao moduleDao;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public ModuleServiceImpl(ModuleDao moduleDao) {
		super((JpaRepository<Module, Long>) moduleDao);
		this.moduleDao = moduleDao;
	}

	/**   
	 * @param id
	 * @throws ServiceException  
	 * @see com.ketayao.security.service.ModuleService#delete(int)  
	 */
	@Transactional
	public void delete(Long id) throws ServiceException {
		if (isRoot(id)) {
			throw new ServiceException("不允许删除根模块。");
		}
		
		Module module = this.get(id);
		
		//先判断是否存在子模块，如果存在子模块，则不允许删除
		if(module.getChildren().size() > 0){
			throw new ServiceException(module.getName() + "模块下存在子模块，不允许删除。");
		}
		moduleDao.delete(module);
	}

	/**   
	 * @param parentId
	 * @param page
	 * @return  
	 * @see com.ketayao.security.service.ModuleService#find(java.lang.Long, com.ketayao.util.dwz.Page)  
	 */
	public List<Module> find(Long parentId, Page page) {
		org.springframework.data.domain.Page<Module> p = 
				moduleDao.findByParentId(parentId, PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, p);
		return p.getContent();
	}

	/**   
	 * @param parentId
	 * @param name
	 * @param page
	 * @return  
	 * @see com.ketayao.security.service.ModuleService#find(java.lang.Long, java.lang.String, com.ketayao.util.dwz.Page)  
	 */
	public List<Module> find(Long parentId, String name, Page page) {
		org.springframework.data.domain.Page<Module> p = 
				moduleDao.findByParentIdAndNameContaining(parentId, name, PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, p);
		return p.getContent();
	}

	/**
	 * 判断是否是根模块.
	 */
	private boolean isRoot(Long id) {
		return id == 1;
	}

	/**
	 * 
	 * @return  
	 * @see com.ketayao.security.service.ModuleService#getTree()
	 */
	public Module getTree() {
		List<Module> list = moduleDao.findAllWithCache();
		
		List<Module> rootList = makeTree(list);
				
		return rootList.get(0);
	}

	private List<Module> makeTree(List<Module> list) {
		List<Module> parent = new ArrayList<Module>();
		// get parentId = null;
		for (Module e : list) {
			if (e.getParent() == null) {
				e.setChildren(new HashSet<Module>(0));
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		
		makeChildren(parent, list);
		
		return parent;
	}
	
	private void makeChildren(List<Module> parent, List<Module> children) {
		if (children.isEmpty()) {
			return ;
		}
		
		List<Module> tmp = new ArrayList<Module>();
		for (Module c1 : parent) {
			for (Module c2 : children) {
				c2.setChildren(new HashSet<Module>(0));
				if (c1.getId().equals(c2.getParent().getId())) {
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}
		
		children.removeAll(tmp);
		
		makeChildren(tmp, children);
	}
	
}
