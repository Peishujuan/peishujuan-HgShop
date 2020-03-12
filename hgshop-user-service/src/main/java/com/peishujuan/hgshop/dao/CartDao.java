package com.peishujuan.hgshop.dao;

import java.util.List;

import com.peishujuan.hgshop.pojo.Cart;

public interface CartDao {

	int add(Cart cart);

	int delete(int[] ids);

	int clear(int uid);

	List<Cart> list(int uid);

	List<Cart> listByIds(int[] cartIds);

	//根据购物车数据id
	void deleteByIds(int[] cartIds);
}
