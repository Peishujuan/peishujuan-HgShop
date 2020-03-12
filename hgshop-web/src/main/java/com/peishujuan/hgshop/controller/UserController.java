package com.peishujuan.hgshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.comm.HgShopConstant;
import com.peishujuan.hgshop.pojo.Cart;
import com.peishujuan.hgshop.pojo.Order;
import com.peishujuan.hgshop.pojo.User;
import com.peishujuan.hgshop.service.CartService;
import com.peishujuan.hgshop.service.OrderService;
import com.peishujuan.hgshop.service.WebUserService;

/**
 * 用于处理与用户相关的请求
 * @author a'su's
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Reference
	WebUserService userService;
	@Reference
	CartService cartService;
	@Reference
	OrderService orderService;
	
	/**
	 * 进入登陆的页面
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "user/login";
	}
	
	/**
	 * 接收用户登录的数据
	 * @param request
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request,String username,String password) {
		// 登录
		User user = userService.login(username, password);
		if(user == null) {
			// 保存页面数据
			//...............  todo
			request.setAttribute("error", "用户名+密码错误");
			return "user/login";
		}
		// 写入session
		request.getSession().setAttribute(HgShopConstant.USEKEY, user);
		return "redirect:home";
	}
	
	/**
	 * 进入个人中心
	 * @param request
	 * @return
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request) {
		return "user/index";
		
	}
	
	/**
	 * 进入注册的页面
	 * @return
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		return "user/register";
	}
	
	/**
	 * 接收用户注册提交的数据
	 * @param request
	 * @return
	 */
	@RequestMapping("register")
	public String login(HttpServletRequest request,User user) {
		// 注册
		User registerUser = userService.register(user);
		if(registerUser == null) {
			request.setAttribute("error", "注册失败！~请重新注册");
			return "user/register";
		}
		//注册成功 跳转到登陆页面
		return "redirect:toLogin";
	}
	
	/**
	 * 加入购物车
	 * @param request
	 * @param skuId
	 * @param buyNum 购买数量
	 * @return
	 */
	@RequestMapping("addCart")
	@ResponseBody
	public String addCart(HttpServletRequest request,int skuId,int buyNum) {
		//获取当前登录的用户
		User loginUser = (User) request.getSession().getAttribute(HgShopConstant.USEKEY);
		if(loginUser==null) {
			return "哎呀咦！```还没登录，肿么加入购物车~";
		}else {
			int result = cartService.addCart(loginUser.getUid(),skuId,buyNum);
			return result>0?"success":"添加failed!";
		}
	}
	
	/**
	 * 购物车列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("cartlist")
	public String cartlist(HttpServletRequest request,
			@RequestParam(defaultValue="1") int pageNum) {
		//获取当前登录的用户
		User loginUser = (User)request.getSession().getAttribute(HgShopConstant.USEKEY);
		if(loginUser==null) {
			request.setAttribute("error", "还没登陆~快去登录~");
			return "error";
		}
		PageInfo<Cart> cartList = cartService.list(loginUser.getUid(), pageNum);
		request.setAttribute("pageInfo", cartList);
		return "user/cartlist";
	}
	
	@RequestMapping("addorder")
	@ResponseBody
	public String addOrder(HttpServletRequest request,@RequestParam("cartIds[]")int[] cartIds,String address) {
		//获取当前登录的用户
		User loginUser = (User)request.getSession().getAttribute(HgShopConstant.USEKEY);
		if(loginUser==null) {
			request.setAttribute("error", "还没登陆~快去登录~");
			return "error";
		}
		System.out.println("cartIds is " + cartIds);
		System.out.println("address is " + address);
		int result = cartService.createOrder(loginUser.getUid(),cartIds,address);
		
		return result>0?"success":"下订单failed";
	}
	
	/**
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("orderlist")
	public String orderlist(HttpServletRequest request,
			@RequestParam(defaultValue="1") int pageNum) {
		//获取当前登录的用户
		User loginUser = (User)request.getSession().getAttribute(HgShopConstant.USEKEY);
		if(loginUser==null) {
			request.setAttribute("error", "您尚未登陆");
			return "error";
		}
		PageInfo<Order> list = orderService.list(loginUser.getUid(), pageNum);
		request.setAttribute("pageInfo", list);
		return "user/orderlist";
		
	}
	
	
}
