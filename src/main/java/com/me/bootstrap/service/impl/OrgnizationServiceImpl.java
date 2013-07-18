/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.service.impl.OrganizationServiceImpl.java
 * Class:			OrganizationServiceImpl
 * Date:			2012-8-27
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.me.bootstrap.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.me.bootstrap.dao.OrgnizationDao;
import com.me.bootstrap.entity.Orgnization;
import com.me.bootstrap.exception.ServiceException;
import com.me.bootstrap.service.OrgnizationService;
import com.me.bootstrap.util.Page;
import com.me.bootstrap.util.PageUtils;
import com.me.bootstrap.util.SearchFilter;


/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-27 下午3:56:46 
 */
@Service("orgnizationService")
@Transactional(readOnly=true)
public class OrgnizationServiceImpl extends BaseServiceImpl<Orgnization, Long> implements OrgnizationService {
	
	private OrgnizationDao orgnizationDao;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public OrgnizationServiceImpl(OrgnizationDao orgnizationDao) {
		super((JpaRepository<Orgnization, Long>) orgnizationDao);
		this.orgnizationDao = orgnizationDao;
	}

	/**   
	 * @param id
	 * @throws ServiceException  
	 * @see com.ketayao.security.service.OrganizationService#delete(java.lang.Long)  
	 */
	@Transactional
	public void delete(Long id) throws ServiceException {
		if (isRoot(id)) {
			throw new ServiceException("不允许删除根组织。");
		}
		
		Orgnization organization = this.get(id);
		
		//先判断是否存在子模块，如果存在子模块，则不允许删除
		if(organization.getChildren().size() > 0){
			throw new ServiceException(organization.getName() + "组织下存在子组织，不允许删除。");
		}
		
		orgnizationDao.delete(id);
	}

	/**   
	 * @param parentId
	 * @param page
	 * @return  
	 * @see com.ketayao.security.service.OrganizationService#find(java.lang.Long, com.ketayao.util.dwz.Page)  
	 */
	public List<Orgnization> find(Long parentId, Page page) {
		org.springframework.data.domain.Page<Orgnization> p = 
				orgnizationDao.findByParentId(parentId, PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, p);
		return p.getContent();
	}

	/**   
	 * @param parentId
	 * @param name
	 * @param page
	 * @return  
	 * @see com.ketayao.security.service.OrganizationService#find(java.lang.Long, java.lang.String, com.ketayao.util.dwz.Page)  
	 */
	public List<Orgnization> find(Long parentId, String name, Page page) {
		org.springframework.data.domain.Page<Orgnization> p = 
				orgnizationDao.findByParentIdAndNameContaining(parentId, name, PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, p);
		return p.getContent();
	}
	
	/**
	 * 判断是否是根组织.
	 */
	private boolean isRoot(Long id) {
		return id == 1;
	}

	/**
	 * 
	 * @return  
	 * @see com.ketayao.security.service.OrganizationService#getTree()
	 */
	public Orgnization getTree() {
		List<Orgnization> list = orgnizationDao.findAllWithCache();
		
		List<Orgnization> rootList = makeTree(list);
				
		return rootList.get(0);
	}

	private List<Orgnization> makeTree(List<Orgnization> list) {
		List<Orgnization> parent = new ArrayList<Orgnization>();
		// get parentId = null;
		for (Orgnization e : list) {
			if (e.getChildren() == null) {
				e.setChildren(new HashSet<Orgnization>(0));
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		
		makeChildren(parent, list);
		
		return parent;
	}
	
	private void makeChildren(List<Orgnization> parent, List<Orgnization> children) {
		if (children.isEmpty()) {
			return ;
		}
		
		List<Orgnization> tmp = new ArrayList<Orgnization>();
		for (Orgnization c1 : parent) {
			for (Orgnization c2 : children) {
				c2.setChildren(new HashSet<Orgnization>(0));
				if (c1.getId().equals(c2.getParent().getId())) {
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}
		
		children.removeAll(tmp);
		
		makeChildren(tmp, children);
	}

	@Override
	public org.springframework.data.domain.Page<Orgnization> findPage(
			Map<String, String[]> params, Pageable pageable) {
		
		 return orgnizationDao.findAll(spec(params),pageable);
	}
	
	private Specification<Orgnization> spec(Map<String, String[]> params) {
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		Specification<Orgnization> sp = SearchFilter.spec(filters, Orgnization.class);
		return sp;
	}
}
