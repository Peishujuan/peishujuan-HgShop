package com.peishujuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Brand;
/**
 * Dubbo 服务接口函数必须要有  !非Void! 的返回值********
 * @param brand
 * @return
 */
public interface BrandService {

	
	int addBrand(Brand brand);
	Brand listBrandById(int id);
	int updateBrand(Brand brand);
	int deleteBrand(Integer id);
//									    首字母			  页数
	PageInfo<Brand> listBrand(String firstChar, int pageNum);
	
	List<Brand> getAllBrands();//获取所有的品牌
}
