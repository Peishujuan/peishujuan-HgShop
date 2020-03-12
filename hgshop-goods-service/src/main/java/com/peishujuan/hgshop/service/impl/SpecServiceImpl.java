package com.peishujuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.dao.SpecDao;
import com.peishujuan.hgshop.pojo.Spec;
import com.peishujuan.hgshop.pojo.SpecOption;
import com.peishujuan.hgshop.service.SpecService;

@Service(interfaceClass = SpecService.class)
public class SpecServiceImpl implements SpecService{

	@Autowired
	SpecDao specDao;
	
	public PageInfo<Spec> list(String name, int pageNum) {
		PageHelper.startPage(pageNum, 3);
		return new PageInfo<Spec>(specDao.list(name));
	}

	public int add(Spec spec) {
		//添加主表
		specDao.addSpec(spec);
		
		//再添加主表的同时，获取id，然后根据获取到的id添加字表
		List<SpecOption> options = spec.getOptions();
		//添加规格
		int n=1;
		for (SpecOption specOption : options) {
			//设置主表id
			specOption.setSpecId(spec.getId());
			specDao.addOption(specOption);
			n++;
		}
		// 返回的是影响数据的条数
		return n;
	}

	public int update(Spec spec) {
		// 去子表中删除
		specDao.deleteSpecOptions(spec.getId());
		// 修改主表
		specDao.updateSpec(spec);	 
		// 插入子表
		List<SpecOption> options = spec.getOptions();
		for (SpecOption specOption : options) {
			// 设置主表的id
			specOption.setSpecId(spec.getId());
			specDao.addOption(specOption);
		}
		
		return 1;
	}

	public int delete(int id) {
		//删除子表
		specDao.deleteSpecOptions(id);
		//删除主表
		specDao.deleteSpec(id);
		return 1;
	}

	public Spec findById(int id) {
		
		return specDao.get(id);
	}

	public int deleteBatch(int[] ids) {
		//删除子表
		specDao.deleteSpecOptionsBatch(ids);
		//删除主表
		specDao.deleteSpecBatch(ids);
		return 1;
	}

	@Override
	public List<Spec> listAll() {
		// TODO Auto-generated method stub
		return specDao.listAll();
	}

}
