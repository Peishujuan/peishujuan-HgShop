package com.peishujuan.hgshop.dao;

import java.util.List;

import com.peishujuan.hgshop.pojo.Spec;
import com.peishujuan.hgshop.pojo.SpecOption;

public interface SpecDao {

	List<Spec> list(String name);

	int addSpec(Spec spec);

	int addOption(SpecOption specOption);

	int updateSpec(Spec spec);

	int deleteSpecOptions(int id);

	int deleteSpec(int id);

	Spec get(int id);

	int deleteSpecOptionsBatch(int[] ids);

	int deleteSpecBatch(int[] ids);

	List<Spec> listAll();

}
