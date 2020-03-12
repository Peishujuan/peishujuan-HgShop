package com.peishujuan.hgshop.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peishujuan.hgshop.pojo.Category;
import com.peishujuan.hgshop.service.CategoryService;
/**
 *  类别管理
 * @author a'su's
 *
 */
@Controller
@RequestMapping("cat")
public class CatetgoryController {

	@Reference
	CategoryService categoryService;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		return "cat/list";
	}
	
	@RequestMapping("treeData")
	@ResponseBody
	public List<Category> treeData(HttpServletRequest request) {
		return categoryService.treeCategory();
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request,
			@RequestParam(defaultValue="0")int parentId,String name ) {
		Category category = new Category();
		category.setParentId(parentId);
		category.setName(name);
		return categoryService.addCategory(category)>0?"success":"failed";
	}
	
	@RequestMapping("del")
	@ResponseBody
	public String del(HttpServletRequest request,
			@RequestParam(defaultValue="0") int id) {
		
		
		return categoryService.deleteCategory(id)>0 ?"success":"failed";
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest request,
			Category cat) {
		System.err.println( "cat is " +  cat);
		return categoryService.updateCategory(cat)>0 ?"success":"failed";
	}
}
