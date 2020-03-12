package com.peishujuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.peishujuan.hgshop.dao.CategoryDao;
import com.peishujuan.hgshop.dao.SpuDao;
import com.peishujuan.hgshop.pojo.Brand;
import com.peishujuan.hgshop.pojo.Category;
import com.peishujuan.hgshop.pojo.Spu;
import com.peishujuan.hgshop.pojo.SpuVo;
import com.peishujuan.hgshop.service.SpuService;

@Service(interfaceClass=SpuService.class)
public class SpuServiceImpl implements SpuService{


	
	@Autowired
	SpuDao spuDao;
	
	@Override
	public PageInfo<Spu> listSpu(int pageNum, SpuVo vo) {
		PageHelper.startPage(pageNum, 10);
		return new PageInfo<Spu>(spuDao.list(vo));
	}

	@Override
	public int addSpu(Spu spu) {
		// TODO Auto-generated method stub
		return spuDao.add(spu);
	}

	@Override
	public int updateSpu(Spu spu) {
		// TODO Auto-generated method stub
		return spuDao.update(spu);
	}

	@Override
	public int deleteSpu(Integer id) {
		// TODO Auto-generated method stub
		return spuDao.delete(id);
	}

	@Override
	public int deleteSpuBatch(int[] ids) {
		// TODO Auto-generated method stub
		return spuDao.deleteSpuBatch(ids);
	}

	@Override
	public Spu getSpu(int id) {
		// TODO Auto-generated method stub
		return spuDao.findById(id);
	}

	

}
