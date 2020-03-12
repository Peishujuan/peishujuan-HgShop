package com.peishujuan.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Order;
import com.peishujuan.hgshop.pojo.OrderDetail;

/**
 * 订单的服务
 * @author a'su's
 *
 */
public interface OrderService {

	//查看订单
	PageInfo<Order> list(int userId,int pageNum);
	
	//查看订单详情
	List<OrderDetail> listDetail(int orderId,int pageNum);
}
