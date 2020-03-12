package com.peishujuan.hgshop.dao;

import java.util.List;

import com.peishujuan.hgshop.pojo.Order;
import com.peishujuan.hgshop.pojo.OrderDetail;

public interface OrderDao {

	int add(Order order);

	int addDetail(OrderDetail orderDetail);

	//显示一列订单
	List<Order> list(int userId);
	
	//显示一个订单的详情
	List<OrderDetail> listDetail(int orderId);
}
