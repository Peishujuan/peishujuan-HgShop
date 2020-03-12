package com.peishujuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.dao.BrandDao;
import com.peishujuan.hgshop.pojo.Brand;
import com.peishujuan.hgshop.service.BrandService;

@Service(interfaceClass=BrandService.class)
public class BrandServiceImpl implements BrandService{

	@Autowired
	BrandDao brandDao;
	
	public int addBrand(Brand brand) {
		
		return brandDao.addBrand(brand);
	}
	//查一条 回显
	@Override
	public Brand listBrandById(int id) {
		
		return brandDao.listBrandById(id);
	}
	//修改
	public int updateBrand(Brand brand) {
		
		return brandDao.updateBrand(brand);
	}

	public int deleteBrand(Integer id) {
		
		return brandDao.deleteBrand(id);
	}

	public PageInfo<Brand> listBrand(String firstChar, int pageNum) {
		PageHelper.startPage(pageNum, 3);
		return new PageInfo<Brand>(brandDao.listBrand(firstChar));
	}
	
	//获取所有的品牌
	@Override
	public List<Brand> getAllBrands() {
		// TODO Auto-generated method stub
		return brandDao.listAll();
	}

}
