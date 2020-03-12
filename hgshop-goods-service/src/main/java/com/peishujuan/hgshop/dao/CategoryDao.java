package com.peishujuan.hgshop.dao;

import java.util.List;

import com.peishujuan.hgshop.pojo.Brand;
import com.peishujuan.hgshop.pojo.Category;

public interface CategoryDao {

	List<Category> tree();

	int add(Category category);

	int update(Category category);

	int delete(Integer id);
	
	






}
