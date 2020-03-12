package com.peishujuan.hgshop.service;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Spu;
import com.peishujuan.hgshop.pojo.SpuVo;

/**
 * 
 * @param spu
 * @return
 */
public interface SpuService {
	
	
	PageInfo<Spu> listSpu(int pageNum,SpuVo vo);
	
	int addSpu(Spu spu);
	int updateSpu(Spu spu);
	int deleteSpu(Integer id);
	int deleteSpuBatch(int[] ids);

	Spu getSpu(int id);

}
