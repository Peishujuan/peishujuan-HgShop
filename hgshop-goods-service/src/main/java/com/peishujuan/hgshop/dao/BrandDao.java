package com.peishujuan.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.peishujuan.hgshop.pojo.Brand;

public interface BrandDao {

	List<Brand> listBrand(String firstChar);

	int addBrand(Brand brand);

	Brand listBrandById(int id);

	int updateBrand(Brand brand);

	int deleteBrand(Integer id);

	//获取所有的品牌
	@Select("SELECT id,name,first_char as firstChar "
			+ "FROM hg_brand "
			+ "where deleted_flag=0 "
			+ "ORDER BY name")           //老师为了少写一个mapper的方法！！
	List<Brand> listAll();
	
	
}
