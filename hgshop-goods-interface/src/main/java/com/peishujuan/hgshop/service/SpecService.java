package com.peishujuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Spec;

public interface SpecService {
	
	PageInfo<Spec> list(String name,int pageNum);
	
	int add(Spec spec);
	int update(Spec spec);
	int delete(int id); 
	
	Spec findById(int id);//查找一个规格 用于修改时候的回显
	int deleteBatch(int[] ids);//批量删除

	List<Spec> listAll();//获取所有的规格名称
}
