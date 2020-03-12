package com.peishujuan.hgshop.dao;

import java.util.List;

import com.peishujuan.hgshop.pojo.Spu;
import com.peishujuan.hgshop.pojo.SpuVo;

public interface SpuDao {

	List<Spu> list(SpuVo vo);

	int add(Spu spu);

	int update(Spu spu);

	int delete(Integer id);

	int deleteSpuBatch(int[] ids);

	Spu findById(int id);

}
