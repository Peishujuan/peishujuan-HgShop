package com.peishujuan.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peishujuan.hgshop.pojo.Sku;
import com.peishujuan.hgshop.pojo.SpecOption;

public interface SkuDao {

	List<Sku> list(Sku sku);
	
	Sku get(int id);
	
	// 添加sku
	int addSku(Sku sku);
	//添加对应sku的属性值
	int addSkuSpec(@Param("skuId") int skuId,@Param("so") SpecOption so);

	List<Sku> listSkuBySpu(int spuId);//获取一个sku的列表 通过spu的id
	
	
}
