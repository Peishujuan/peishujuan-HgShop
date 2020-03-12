package com.peishujuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Category;
/**
 * Dubbo 服务接口函数必须要有  !非Void! 的返回值********
 * @param category
 * @return
 */
public interface CategoryService {


	int addCategory(Category category);
	int updateCategory(Category category);
	int deleteCategory(Integer id);
//									    首字母			  页数
	PageInfo<Category> listCategory(String firstChar, int pageNum);
	
	List<Category> treeCategory();//以树的形式显示列表
}
