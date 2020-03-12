package com.peishujuan.hgshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Spec;
import com.peishujuan.hgshop.service.SpecService;
/**
 * 规格管理
 * @author a'su's
 *
 */
@Controller
@RequestMapping("spec")
public class SpecController {

	@Reference
	SpecService specService;
	
	/**
	 * 进入规格列表
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request,String name, @RequestParam(defaultValue = "1")int pageNum) {
		PageInfo<Spec> pageInfo = specService.list(name, pageNum);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("queryName", name);
		return "spec/list";
	}
	
	/**
	 * 添加
	 * @param request
	 * @param name
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request,Spec spec) {
		//System.out.println("spec"+spec);
		spec.getOptions().removeIf(x->{return x.getOptionName()==null;});//lamda表达式
		//System.out.println("spec处理后" + spec);
		
		//调用服务
		int add = specService.add(spec);
		return add>0?"success":"false";
	}
	
	/**
	 *  删除规格
	 * @param request
	 * @param id  规格的id
	 * @return
	 */
	@RequestMapping("delSpec")
	@ResponseBody
	public String delSpec(HttpServletRequest request,int id) {
		//调用服务
		int delNum = specService.delete(id);
		return delNum>0?"success":"false";
	}
	
	/**
	  *   批量删除规格
	 * @param request
	 * @param id  规格的id
	 * @return
	 */
	@RequestMapping("delSpecBatch")
	@ResponseBody
	public String delSpecBatch(HttpServletRequest request,@RequestParam(name="ids[]")int[] ids) {
		for (int i : ids) {
			System.out.println("要删除的数据----------"+i);
		}
		//调用服务
		int delNum = specService.deleteBatch(ids);
		return delNum>0?"success":"false";
	}
	
	/**
	  * 修改数据时候的回显
	 * @param request
	 * @param 规格id
	 * @return
	 */
	@RequestMapping("getSpec")
	@ResponseBody
	public Spec getSpec(HttpServletRequest request, int id){
		return specService.findById(id);
	}
	/**
	  * 修改
	 * @param request
	 * @param spec
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest request,Spec spec) {
		System.out.println("spec" + spec);
		//System.out.println();
		spec.getOptions().removeIf(x->{return x.getOptionName()==null;});
		System.out.println("spec 处理后：" + spec);
		//调用服务
		int result = specService.update(spec);  
		return result>0?"success":"false";
		//return "fail";
	}
}
