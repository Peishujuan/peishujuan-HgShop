package com.peishujuan.hgshop.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.peishujuan.hgshop.dao.UserDao;
import com.peishujuan.hgshop.pojo.User;
import com.peishujuan.hgshop.service.WebUserService;

/**
 * 用户的服务   这里用户是普通用户 也就是购物的消费者
 * @author a'su's
 *
 */
@Service(interfaceClass = WebUserService.class)
public class WebUserServiceImpl implements WebUserService{

	@Autowired
	UserDao userDao;
	
	//登录
	@Override
	public User login(String username, String pwd) {
		//记得加密
		return userDao.find(username,DigestUtils.md5Hex(pwd));
	}

	//注册
	@Override
	public User register(User user) {
		// 明文转换成密文
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		//添加成功
		if(userDao.add(user)>0) {
			// 根据自增id 再反查一次用户
			return userDao.getById(user.getUid());
		}
		//添加失败
		return null;
	}

}
