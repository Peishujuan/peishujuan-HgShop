package com.peishujuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Sku;
import com.peishujuan.hgshop.pojo.Spec;
import com.peishujuan.hgshop.pojo.Spu;

public interface SkuService {

	// sku的管理
	PageInfo<Sku>  listSku(int pageNum,Sku sku);
	int addSku(Sku sku);
	Sku getSku(int id);//获取详情
	int updateSku(Sku sku);
	int deleteSku(int id);
	int deleteSkuBatch(int[] ids);
	
	Spu getSpu(int id);
	
	List<Sku> listSkuBySpu(int spuId);//根据spu 获取所有的sku
	
}
