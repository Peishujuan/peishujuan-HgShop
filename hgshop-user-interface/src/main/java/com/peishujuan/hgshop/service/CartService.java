package com.peishujuan.hgshop.service;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Cart;
/**
 * 购物车的服务
 * @author a'su's
 *
 */
public interface CartService {

	//添加                       用户id                  购买数量
	int addCart(Integer uid, int skuId, int buyNum);

	//删除
	int deleteCart(int[] ids);
	
	//清空购物车
	int clearCart(int uid);
	
	//查看购物车
	PageInfo<Cart> list(int uid,int pageNum);

	//添加订单
	int createOrder(Integer uid, int[] cartIds, String address);
}
