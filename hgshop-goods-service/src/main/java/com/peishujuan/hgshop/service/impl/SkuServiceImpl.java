package com.peishujuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.dao.SkuDao;
import com.peishujuan.hgshop.dao.SpuDao;
import com.peishujuan.hgshop.pojo.Sku;
import com.peishujuan.hgshop.pojo.Spec;
import com.peishujuan.hgshop.pojo.SpecOption;
import com.peishujuan.hgshop.pojo.Spu;
import com.peishujuan.hgshop.service.SkuService;

@Service(interfaceClass = SkuService.class)
public class SkuServiceImpl implements SkuService{

	@Autowired
	SkuDao skuDao;
	@Autowired
	SpuDao spuDao;
	
	@Override
	public PageInfo<Sku> listSku(int pageNum, Sku sku) {
		PageHelper.startPage(pageNum, 5);
		return new PageInfo<Sku>(skuDao.list(sku));
	}

	@Override
	public int addSku(Sku sku) {
		//先加主表
				int cnt = skuDao.addSku(sku);
				List<SpecOption> specs = sku.getSpecs();
				for (SpecOption specOption : specs) {
					cnt += skuDao.addSkuSpec(sku.getId(),specOption);
				}
		return cnt;
	}

	@Override
	public Sku getSku(int id) {
		// TODO Auto-generated method stub
		return skuDao.get(id);
	}

	@Override
	public int updateSku(Sku sku) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSku(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSkuBatch(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Spu getSpu(int id) {
		// TODO Auto-generated method stub
		return spuDao.findById(id);
	}

	
	@Override
	public List<Sku> listSkuBySpu(int spuId) {
		// TODO Auto-generated method stub
		return skuDao.listSkuBySpu(spuId);
	}





}
