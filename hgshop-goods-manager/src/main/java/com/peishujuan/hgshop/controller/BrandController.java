package com.peishujuan.hgshop.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Brand;
import com.peishujuan.hgshop.service.BrandService;




/**
 * 品牌管理
 * @author a'su's
 *
 */
@Controller
@RequestMapping("brand")
public class BrandController {

	@Reference
	BrandService brandService;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,String firstChar,@RequestParam(defaultValue = "1")int pageNum) {
		PageInfo<Brand> pageInfo = brandService.listBrand(firstChar,pageNum);
		request.setAttribute("pageInfo", pageInfo);
		return "brand/list";
	}
	
	@ResponseBody
	@RequestMapping("add")
	public Object  add(Brand brand) {
		int add = brandService.addBrand(brand);
		return add>0?"success":"false";
	}
	
	@ResponseBody
	@RequestMapping("upda")
	public Brand upda(int id) {
		
		return brandService.listBrandById(id);
	}
	
	@ResponseBody
	@RequestMapping("upd")
	public Object update(Brand brand) {
		System.err.println(brand);
		return brandService.updateBrand(brand);
	}
	@ResponseBody
	@RequestMapping("delspec")
	public Object delspec(int id) {

		return brandService.deleteBrand(id);
	}
}
