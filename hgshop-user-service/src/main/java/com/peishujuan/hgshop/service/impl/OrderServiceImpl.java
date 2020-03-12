package com.peishujuan.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.dao.OrderDao;
import com.peishujuan.hgshop.pojo.Order;
import com.peishujuan.hgshop.pojo.OrderDetail;
import com.peishujuan.hgshop.service.OrderService;

@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDao orderDao;
	
	@Override
	public PageInfo<Order> list(int userId, int pageNum) {
		PageHelper.startPage(pageNum, 10);
		return new PageInfo<Order>(orderDao.list(userId));
	}

	@Override
	public List<OrderDetail> listDetail(int orderId, int pageNum) {
		
		return orderDao.listDetail(orderId);
	}

}
