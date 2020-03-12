package com.peishujuan.hgshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Sku;
import com.peishujuan.hgshop.pojo.Spu;
import com.peishujuan.hgshop.pojo.SpuVo;
import com.peishujuan.hgshop.service.SkuService;
import com.peishujuan.hgshop.service.SpuService;
/**
 * 首页
 * @author a'su's
 *
 */
@Controller
public class IndexController {

	@Reference
	SpuService spuService;
	@Reference
	SkuService skuService;
	
	@RequestMapping({"/","index"})
	public String index(HttpServletRequest request,
			@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="0")int catId) {
		PageInfo<Spu> listSpu = spuService.listSpu(pageNum, new SpuVo());
		request.setAttribute("pageInfo", listSpu);
				return "index";
	}

	/**
	 * 显示商品详情
	 * @param request
	 * @param spuId
	 * @return
	 */
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,int spuId) {
		/**
		 * 获取详情
		 */
		// spu
		Spu spu = spuService.getSpu(spuId);
		//sku 
		List<Sku> skuList = skuService.listSkuBySpu(spuId);
		
		request.setAttribute("spu", spu);
		request.setAttribute("skus", skuList);
		
		System.out.println("skuList is " + skuList);
		return "detail";
	}
}
