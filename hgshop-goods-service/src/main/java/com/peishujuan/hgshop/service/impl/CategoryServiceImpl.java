package com.peishujuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.dao.CategoryDao;
import com.peishujuan.hgshop.pojo.Category;
import com.peishujuan.hgshop.service.CategoryService;

@Service(interfaceClass=CategoryService.class)
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao catDao;
	
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return catDao.add(category);
	}

	public int updateCategory(Category category) {
		// TODO Auto-generated method stub
		return catDao.update(category);
	}

	public int deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		return catDao.delete(id);
	}

	public PageInfo<Category> listCategory(String firstChar, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> treeCategory() {
		// TODO Auto-generated method stub
		return catDao.tree();
	}

}
