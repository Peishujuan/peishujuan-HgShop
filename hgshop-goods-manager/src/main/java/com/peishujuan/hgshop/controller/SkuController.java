package com.peishujuan.hgshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Sku;
import com.peishujuan.hgshop.pojo.Spec;
import com.peishujuan.hgshop.pojo.SpecOption;
import com.peishujuan.hgshop.pojo.Spu;
import com.peishujuan.hgshop.service.SkuService;
import com.peishujuan.hgshop.service.SpecService;
import com.peishujuan.hgshop.service.SpuService;


@Controller
@RequestMapping("goods")
public class SkuController {

	@Reference
	SkuService skuService;
	@Reference
	SpecService specService;

	
	@RequestMapping("skulist")
	public String skulist(HttpServletRequest request ,
			@RequestParam (defaultValue="1") int pageNum,Sku sku) {
		
		PageInfo<Sku> listSku = skuService.listSku(pageNum, sku);
		request.setAttribute("pageInfo", listSku);
		return "sku/list";
	}
	
	@RequestMapping("skuDetail")
	public String skulist(HttpServletRequest request ,int id) {
		Sku sku = skuService.getSku(id);
		request.setAttribute("sku", sku);
		return "sku/detail";
	}
	
	/**
	 * 跳转到sku添加页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toaddSku")
	public String toaddSku(HttpServletRequest request ,int spuId) {
		// 获取要添加的商品
		Spu spu = skuService.getSpu(spuId);
		request.setAttribute("spu", spu);
		
	/*	// 获取所有的品牌
		List<Brand> brands = goodService.getAllBrands();
		request.setAttribute("brands", brands);*/
		
		// 属性名称
		List<Spec> list = specService.listAll();
		System.out.println("list is " + list);
		request.setAttribute("specs", list);
		
		return "sku/add";
	}
	
	@RequestMapping("addSku")
	@ResponseBody
	public String addSku(HttpServletRequest request ,Sku sku,int[] specIds,
			@RequestParam(value="specOptionIds") int[] specOptionIds) {
		
		// 保存给sku的所有的属性以及属性值
		List<SpecOption> specs = new ArrayList<>();
		
		System.out.println("specIds + " + specIds.length + " and specOptionIds is " + specOptionIds.length);
		
		
		for (int i = 0; i < specIds.length && i < specOptionIds.length; i++) {
			int j = specIds[i];
			SpecOption specOption = new SpecOption();
			//属性的id
			specOption.setSpecId(specIds[i]);
			// 具体的属性值 的id
			specOption.setId(specOptionIds[i]);
			specs.add(specOption);
		}
		//存放属性的数据
		sku.setSpecs(specs);
		int addSku = skuService.addSku(sku);
		
		return addSku>0?"success":"failed";
	}
	
	
}
