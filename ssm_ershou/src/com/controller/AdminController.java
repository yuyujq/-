package com.controller;
/**
 * yujq
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Admins;
import com.entity.Goods;
import com.entity.Orders;
import com.entity.Tops;
import com.entity.Types;
import com.entity.Users;
import com.entity.tixing;
import com.service.AdminService;
import com.service.GoodService;
import com.service.OrderService;
import com.service.TopService;
import com.service.TypeService;
import com.service.UserService;
import com.service.tixingService;
import com.util.PageUtil;
import com.util.SafeUtil;
import com.util.UploadUtil;

import jdk.nashorn.internal.runtime.Timing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台相关接口
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final int rows = 10;

	@Autowired
	private AdminService adminService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private GoodService goodService;
	@Autowired
	private TopService topService;
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private tixingService tixingService;
	

	/**
	 * 管理员登录
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Admins admin, HttpServletRequest request, HttpSession session) {
		if (adminService.checkUser(admin.getUsername(), admin.getPassword())) {
			session.setAttribute("username", admin.getUsername());
			if(admin.getUsername().equals("shangjia")||admin.getUsername().equals("666"))
			{
				return "redirect:index1.jsp";
			}
			else {
				return "redirect:index";	
			}
			
		}
		request.setAttribute("msg", "用户名或密码错误!");
		return "/admin/login.jsp";
	}

	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "/admin/login.jsp";
	}
	
	/**
	 * 后台首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("msg", "恭喜你! 登录成功了");
		
		
		return "/admin/index.jsp";
	}

	@RequestMapping("/orderListmap")
	public String  orderListmap(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request,
			@RequestParam(required=false, defaultValue="6") int page) {
		 
		/*
         
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
		 
			map.put("rows", orderService.getList(status, page, rows)); 
			map.put("success",true);
		 
		} catch (Exception e) {
			// TODO: handle exception
			map.put("success",false);
			map.put("msg","查询失败");
			return map;
		}
		return map;*/
		 System.out.println("HelloWorld12312123");
		 status=4;
		 
		 List<Orders>  list=orderService.getListVgoods(status, 1, 100);
		 System.out.println(" list.getsiz() "+list.size());
		 
		 
		 List<String> name=new ArrayList<String>(100);
		 List<Integer> valuess=new ArrayList<Integer>();
		 
		 
		 for (int i = 0; i < list.size(); i++) {
			
			 System.out.println(" list.get:  "+list.get(i).getName()+" name:::  "+name.size());
			  
			 if(!name.contains("'"+list.get(i).getName()+"'"))
			 {    
				  
				 name.add("'"+list.get(i).getName()+"'");
			  	 valuess.add(list.get(i).getTotal());
			 } 
			 else {       //如果数组中包含此商品名字 ，只更新数量
				 
	          	Integer aaa= name.indexOf("'"+list.get(i).getName()+"'");//找到此产品在名字数组中的索引位置
	          	 System.out.println(" aaa "+aaa);
	           Integer bbb= valuess.get(aaa)+list.get(i).getTotal();// 数量valuess集合中根据索引得到当前产品名对应的数量，并加上当前数量
	            valuess.set(aaa, bbb);//将新数量重新放到当前索引位置
	         
			}

			 
		} 
		 
		//将数据整理成前台需要的格式
		 String valuelast="";
		 for (int i = 0; i < valuess.size(); i++) {
			  valuelast+=valuess.get(i)+",";
		} 
	  	 valuelast=valuelast.substring(0,valuelast.length()-1);
		  
		System.out.println(" name: "+name+"  valuelast:"+valuelast);
		request.setAttribute("flag", 6);
		request.setAttribute("status", status);
		request.setAttribute("dataones",name);//"['aaa','bbb']"
		request.setAttribute("shuliang", valuelast);//"20,30"
		 
		request.setAttribute("orderList", orderService.getList(status, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, orderService.getTotal(status), page, rows));
		
		return "/admin/tongji.jsp";
		
		
	}
	
	
	
	/**
	 * 订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/orderList")
	public String orderList(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request,
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 1);
		request.setAttribute("status", status);
		request.setAttribute("orderList", orderService.getList(status, page, rows));
		if(!orderService.getList(status, page, rows).isEmpty()&&orderService.getList(status, page, rows)!=null) {
		request.setAttribute("pageTool", PageUtil.getPageTool(request, orderService.getTotal(status), page, rows));
		}else {
			request.setAttribute("Npage", 1);
		}
		return "/admin/order_list.jsp";
	}
//	@RequestMapping("/orderList1")
//	public String orderList1(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request,
//			@RequestParam(required=false, defaultValue="1") int page) {
//		request.setAttribute("flag", 1);
//		request.setAttribute("status", status);
//		request.setAttribute("orderList", orderService.getList1(page, rows));
//		request.setAttribute("pageTool", PageUtil.getPageTool(request, orderService.getTotal(status), page, rows));
//		return "/admin/order_list.jsp";
//	}
	
	/**
	 * 订单查询
	 * 
	 * @return
	 */
	@RequestMapping("/orderSel")
	public String searchOrder(int id, @RequestParam(required=false, defaultValue="1")int page, HttpServletRequest request) {
		System.out.println(id+page);
		if (Objects.nonNull(id) && id!=0) {
			if(orderService.gett(id).get(0)!=null) {
			request.setAttribute("orderList", orderService.gett(id));
			request.setAttribute("flag", 1);
			request.setAttribute("status",orderService.gett(id).get(0).getStatus());
			System.out.println(orderService.gett(id));
			if(!orderService.gett(id).isEmpty()&&orderService.gett(id)!=null) {
			System.out.println(2);
			request.setAttribute("pageTool", PageUtil.getPageTool(request, 1, page, rows));}

			}else {
				request.setAttribute("Npage", 1);
			}
		}
		return "/admin/order_list.jsp";
	} 
	/**
	 * 订单发货
	 * 
	 * @return
	 */
	@RequestMapping("/orderDispose")
	public String orderDispose(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		orderService.dispose(id);
		return "redirect:orderList?flag=1&status="+status+"&page="+page;
	}
	
	/**
	 * 订单完成
	 * 
	 * @return
	 */
	@RequestMapping("/orderFinish")
	public String orderFinish(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		orderService.finish(id);
		return "redirect:orderList?flag=1&status="+status+"&page="+page;
	}

	/**
	 * 订单删除
	 * 
	 * @return
	 */
	@RequestMapping("/orderDelete")
	public String orderDelete(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		orderService.delete(id);
		return "redirect:orderList?flag=1&status="+status+"&page="+page;
	}

	/**
	 * 顾客管理
	 * 
	 * @return
	 */
	@RequestMapping("/userList")
	public String userList(HttpServletRequest request,
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 2);
		request.setAttribute("userList", userService.getList(page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, userService.getTotal(), page, rows));
		return "/admin/user_list.jsp";
	}
	/**
	 * 顾客查询
	 * 
	 * @return
	 */
	@RequestMapping("/userSel")
	public String searchUser(String name, @RequestParam(required=false, defaultValue="1")int page, HttpServletRequest request) {
		request.setAttribute("flag", 2);
		if (Objects.nonNull(name) && !name.trim().isEmpty()) {
			if(userService.gett(name,page,rows)!=null&&!userService.gett(name,page,rows).isEmpty()) {
			request.setAttribute("userList", userService.gett(name,page,rows));
			request.setAttribute("pageTool", PageUtil.getPageTool(request, userService.getByUsername(name), page, rows));
			}else {
				request.setAttribute("Npage", 1);
			}
		}
		return "/admin/user_list.jsp";
	} 
	/**
	 * 通知提醒管理
	 * 
	 * @return
	 */
	 
		@RequestMapping("/tixingList")
		public String tixingList(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request,
				@RequestParam(required=false, defaultValue="1") int page) {
			request.setAttribute("flag", 7);
			request.setAttribute("status", status);
			request.setAttribute("tixingList", tixingService.getList(status, page, rows));
			if(tixingService.getList(status, page, rows).size()==0) {
				request.setAttribute("flag", 0);
			}
			request.setAttribute("pageTool", PageUtil.getPageTool(request, tixingService.getTotal(status), page, rows));
			return "/admin/tixing_list.jsp";
		}
	/**
	 * 留言提醒管理
    * 
	* @return
	 */
		@RequestMapping("/mmsgList")
		public String mmsgList(@RequestParam(required=false, defaultValue="1")byte status, HttpServletRequest request,
				@RequestParam(required=false, defaultValue="1") int page) {
			request.setAttribute("flag", 8);
			request.setAttribute("status", status);
			if(tixingService.getListByTotal(status,page,rows)!=null&&!tixingService.getListByTotal(status,page,rows).isEmpty()) {
			request.setAttribute("tixingList", tixingService.getListByTotal(status,page,rows));
			request.setAttribute("pageTool", PageUtil.getPageTool(request, tixingService.getByTotal(status), page, rows));}
			else {
				request.setAttribute("Npage", 1);
			}
			return "/admin/mmsg_list.jsp";
		}
	/**
	 * 修改回復提醒
	 */
		@RequestMapping("/changemmsg")
		public String confrimMmsg(int id, @RequestParam(required=false, defaultValue="1")byte status,HttpServletRequest request) {
			request.setAttribute("flag", 8);
			request.setAttribute("status", status);
			request.setAttribute("tixing", tixingService.get(id));
			return "/admin/mmsg_edit.jsp";
		}
		@RequestMapping("/mmsgSave")
		public String saveMmsg(tixing t,@RequestParam(required=false, defaultValue="1")byte status,@RequestParam(required=false, defaultValue="1") int page) {
			tixing t1 = tixingService.get(t.getId());
			t1.setName(t.getName());
			t1.setAddress(t.getAddress());
			t1.setTotal(2);
			tixingService.updateById(t1);
			return "redirect:mmsgList?status="+status;
		}
		
	/**
	 * 留言删除
	 * 
	 */
		@RequestMapping("/delmmsg")
		public String delMmsg(int id,@RequestParam(required=false, defaultValue="1")byte status) {
			tixingService.delete(id);
			return "redirect:mmsgList?status="+status;
		}
		
	/**
	 * 根据商品ID搜索留言
	 */
		@RequestMapping("/searchmmsg")
		public String searchMmsg(int id,@RequestParam(required=false, defaultValue="1")byte status,HttpServletRequest request,
				@RequestParam(required=false, defaultValue="1") int page) {
			request.setAttribute("flag", 8);
			if(status == 1) {
				request.setAttribute("status", status);
				request.setAttribute("tixingList", tixingService.getListByAmount1(id,page,rows));
				request.setAttribute("pageTool", PageUtil.getPageTool(request, tixingService.getTotalAmountT1(id), page, rows));
			}else if(status == 2) {
				request.setAttribute("status", status);
				request.setAttribute("tixingList", tixingService.getListByAmount2(id,page,rows));
				request.setAttribute("pageTool", PageUtil.getPageTool(request, tixingService.getTotalAmountT2(id), page, rows));
			}
			return "/admin/mmsg_list.jsp";
		}
	/**
	 * 顾客添加
	 * 
	 * @return
	 */
	@RequestMapping("/userAdd")
	public String userAdd(HttpServletRequest request) {
		request.setAttribute("flag", 2);
		return "/admin/user_add.jsp";
	}

	/**
	 * 顾客添加
	 * 
	 * @return
	 */
	@RequestMapping("/userSave")
	public String userSave(Users user, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		if (userService.isExist(user.getUsername())) {
			request.setAttribute("msg", "用户名已存在!");
			return "/admin/user_add.jsp";
		}
		userService.add(user);
		return "redirect:userList?flag=2&page="+page;
	}

	/**
	 * 顾客密码重置页面
	 * 
	 * @return
	 */
	@RequestMapping("/userRe")
	public String userRe(int id, HttpServletRequest request) {
		request.setAttribute("flag", 2);
		request.setAttribute("user", userService.get(id));
		return "/admin/user_reset.jsp";
	}

	/**
	 * 顾客密码重置
	 * 
	 * @return
	 */
	@RequestMapping("/userReset")
	public String userReset(Users user, 
			@RequestParam(required=false, defaultValue="1") int page) {
		String password = SafeUtil.encode(user.getPassword());
		user = userService.get(user.getId());
		user.setPassword(password);
		userService.update(user);
		return "redirect:userList?flag=2&page="+page;
	}

	/**
	 * 顾客更新
	 * 
	 * @return
	 */
	@RequestMapping("/userEdit")
	public String userEdit(int id, HttpServletRequest request) {
		request.setAttribute("flag", 2);
		request.setAttribute("user", userService.get(id));
		return "/admin/user_edit.jsp";
	}

	/**
	 * 顾客更新
	 * 
	 * @return
	 */
	@RequestMapping("/userUpdate")
	public String userUpdate(Users user, 
			@RequestParam(required=false, defaultValue="1") int page) {
		userService.update(user);
		return "redirect:userList?flag=2&page="+page;
	}

	/**
	 * 顾客删除
	 * 
	 * @return
	 */
	@RequestMapping("/userDelete")
	public String userDelete(Users user, 
			@RequestParam(required=false, defaultValue="1") int page) {
		userService.delete(user);
		return "redirect:userList?flag=2&page="+page;
	}
	
	/**
	 * 提醒删除
	 * 
	 * @return
	 */
	@RequestMapping("/tixingDelete")
	public String tixingDelete(tixing _tixing, 
			@RequestParam(required=false, defaultValue="1") int page) {
		tixingService.delete(_tixing);
		return "redirect:tixingList?flag=2&page="+page;
	}


	/**
	 * 产品列表
	 * 
	 * @return
	 */
	@RequestMapping("/goodList")
	public String goodList(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 3);
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		request.setAttribute("goodList", goodService.getList(status, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotal(status), page, rows));
		return "/admin/good_list.jsp";
	}

	/**
	 * 产品列表
	 * 
	 * @return
	 */
	@RequestMapping("/goodList1")
	public String goodList1(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 3);
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		request.setAttribute("goodList", goodService.getList(status, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotal(status), page, rows));
		return "/admin/good_list1.jsp";
	}
	/**
	 * 产品查询
	 * 
	 * @return
	 */
	@RequestMapping("/goodSel")
	public String search(String name, @RequestParam(required=false, defaultValue="1")int page, HttpServletRequest request) {
		request.setAttribute("flag", 3);
		request.setAttribute("status", 0);
		if (Objects.nonNull(name) && !name.trim().isEmpty()) {
			if(goodService.getListByName(name, page, rows)!=null&&!goodService.getListByName(name, page, rows).isEmpty()) {
			request.setAttribute("goodList", goodService.getListByName(name, page, rows));
			request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotalByName(name), page, rows));
			}
			else {
				request.setAttribute("Npage", 1);
			}
		}
		return "/admin/good_list.jsp";
	} 
	
	/**
	 * 产品添加
	 * 
	 * @return
	 */
	@RequestMapping("/goodAdd")
	public String goodAdd(HttpServletRequest request) {
		request.setAttribute("flag", 3);
		request.setAttribute("typeList", typeService.getList());
		return "/admin/good_add.jsp";
	}

	/**
	 * 产品添加
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/goodSave")
	public String goodSave(String name, int price, String intro, int stock, int typeId, 
			MultipartFile cover, MultipartFile image1, MultipartFile image2, 
			@RequestParam(required=false, defaultValue="1") int page) throws Exception {
		Goods good = new Goods();
		good.setName(name);
		good.setPrice(price);
		good.setIntro(intro);
		good.setStock(stock);
		good.setTypeId(typeId);
		good.setCover(UploadUtil.fileUpload(cover));
		good.setImage1(UploadUtil.fileUpload(image1));
		good.setImage2(UploadUtil.fileUpload(image2));
		goodService.add(good);
		return "redirect:goodList?flag=3&page="+page;
	}

	/**
	 * 产品更新
	 * 
	 * @return
	 */
	@RequestMapping("/goodEdit")
	public String goodEdit(int id,@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request) {
		request.setAttribute("flag", 3);
		request.setAttribute("status", status);
		request.setAttribute("typeList", typeService.getList());
		request.setAttribute("good", goodService.get(id));
		return "/admin/good_edit.jsp";
	}

	/**
	 * 产品更新
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/goodUpdate")
	public String goodUpdate(int id, String name, int price, String intro, int stock, int typeId,  
			MultipartFile cover, MultipartFile image1, MultipartFile image2,
			@RequestParam(required=false, defaultValue="1") int page,@RequestParam(required=false, defaultValue="0")byte status) throws Exception {
		Goods good = goodService.get(id);
		good.setName(name);
		good.setPrice(price);
		good.setIntro(intro);
		good.setStock(stock);
		good.setTypeId(typeId);
		if (Objects.nonNull(cover) && !cover.isEmpty()) {
			good.setCover(UploadUtil.fileUpload(cover));
		}
		if (Objects.nonNull(image1) && !image1.isEmpty()) {
			good.setImage1(UploadUtil.fileUpload(image1));
		}
		if (Objects.nonNull(image2) && !image2.isEmpty()) {
			good.setImage2(UploadUtil.fileUpload(image2));
		}
		goodService.update(good);
		return "redirect:goodList?flag=3&page="+page+"&status="+status;
	}

	/**
	 * 产品删除
	 * 
	 * @return
	 */
	@RequestMapping("/goodDelete")
	public String goodDelete(int id, 
			@RequestParam(required=false, defaultValue="1") int page,@RequestParam(required=false,defaultValue="0")byte status) {
		goodService.delete(id);
		return "redirect:goodList?flag=3&page="+page+"&status="+status;
	}

	
	/**
	 * 添加推荐
	 * @return
	 */
	@RequestMapping("/topSave")
	public @ResponseBody String topSave(Tops tops, 
			@RequestParam(required=false, defaultValue="0")byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		int id = topService.add(tops);
		return id > 0 ? "ok" : null;
	}
	
	/**
	 * 删除推荐
	 * @return
	 */
	@RequestMapping("/topDelete")
	public @ResponseBody String topDelete(Tops tops, 
			@RequestParam(required=false, defaultValue="0")byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		boolean flag = topService.delete(tops);
		return flag ? "ok" : null;
	}

	/**
	 * 类目列表
	 * 
	 * @return
	 */
	@RequestMapping("/typeList")
	public String typeList(HttpServletRequest request) {
		request.setAttribute("flag", 4);
		request.setAttribute("typeList", typeService.getList());
		return "/admin/type_list.jsp";
	}

	/**
	 * 类目添加
	 * 
	 * @return
	 */
	@RequestMapping("/typeSave")
	public String typeSave(Types type, 
			@RequestParam(required=false, defaultValue="1") int page) {
		typeService.add(type);
		return "redirect:typeList?flag=4&page="+page;
	}

	/**
	 * 类目更新
	 * 
	 * @return
	 */
	@RequestMapping("/typeEdit")
	public String typeUp(int id, HttpServletRequest request) {
		request.setAttribute("flag", 4);
		request.setAttribute("type", typeService.get(id));
		return "/admin/type_edit.jsp";
	}

	/**
	 * 类目更新
	 * 
	 * @return
	 */
	@RequestMapping("/typeUpdate")
	public String typeUpdate(Types type, 
			@RequestParam(required=false, defaultValue="1") int page) {
		typeService.update(type);
		return "redirect:typeList?flag=4&page="+page;
	}

	/**
	 * 类目删除
	 * 
	 * @return
	 */
	@RequestMapping("/typeDelete")
	public String typeDelete(Types type, 
			@RequestParam(required=false, defaultValue="1") int page) {
		typeService.delete(type);
		return "redirect:typeList?flag=4&page="+page;
	}

	/**
	 * 管理员列表
	 * 
	 * @return
	 */
	@RequestMapping("/adminList")
	public String adminList(HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 5);
		request.setAttribute("adminList", adminService.getList(page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, adminService.getTotal(), page, rows));
		return "/admin/admin_list.jsp";
	}

	/**
	 * 管理员修改自己密码
	 * 
	 * @return
	 */
	@RequestMapping("/adminRe")
	public String adminRe(HttpServletRequest request, HttpSession session) {
		request.setAttribute("flag", 5);
		request.setAttribute("admin", adminService.getByUsername(String.valueOf(session.getAttribute("username"))));
		return "/admin/admin_reset.jsp";
	}

	/**
	 * 管理员修改自己密码
	 * 
	 * @return
	 */
	@RequestMapping("/adminReset")
	public String adminReset(Admins admin, HttpServletRequest request) {
		request.setAttribute("flag", 5);
		if (adminService.get(admin.getId()).getPassword().equals(SafeUtil.encode(admin.getPassword()))) {
			admin.setPassword(SafeUtil.encode(admin.getPasswordNew()));
			adminService.update(admin);
			request.setAttribute("admin", admin);
			request.setAttribute("msg", "修改成功!");
		}else {
			request.setAttribute("msg", "原密码错误!");
		}
		return "/admin/admin_reset.jsp";
	}

	/**
	 * 管理员添加
	 * 
	 * @return
	 */
	@RequestMapping("/adminSave")
	public String adminSave(Admins admin, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		if (adminService.isExist(admin.getUsername())) {
			request.setAttribute("msg", "用户名已存在!");
			return "/admin/admin_add.jsp";
		}
		adminService.add(admin);
		return "redirect:adminList?flag=5&page="+page;
	}

	/**
	 * 管理员修改
	 * 
	 * @return
	 */
	@RequestMapping("/adminEdit")
	public String adminEdit(int id, HttpServletRequest request) {
		request.setAttribute("flag", 5);
		request.setAttribute("admin", adminService.get(id));
		return "/admin/admin_edit.jsp";
	}

	/**
	 * 管理员更新
	 * 
	 * @return
	 */
	@RequestMapping("/adminUpdate")
	public String adminUpdate(Admins admin, 
			@RequestParam(required=false, defaultValue="1") int page) {
		admin.setPassword(SafeUtil.encode(admin.getPassword()));
		adminService.update(admin);
		return "redirect:adminList?flag=5&page="+page;
	}

	/**
	 * 管理员删除
	 * 
	 * @return
	 */
	@RequestMapping("/adminDelete")
	public String adminDelete(Admins admin, 
			@RequestParam(required=false, defaultValue="1") int page) {
		adminService.delete(admin);
		return "redirect:adminList?flag=5&page="+page;
	}
	
	/**
	 * 新闻列表
	 * @return 
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/newsList")
	public String newsList(HttpServletRequest request) {
		request.setAttribute("flag", 9);
		request.setAttribute("newslist", tixingService.getListByAmount3());
		return "/admin/news_list.jsp";
	}
	/**
	 * 修改新闻
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/newsEdit")
	public String newsEdit(int id,HttpServletRequest request) {
		request.setAttribute("news", tixingService.get(id));
		request.setAttribute("flag", 9);
		return "/admin/news_edit.jsp";
		
	}
	/**
	 * 新闻保存
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/newsSave")
	public String newsSave(int id,String name, String content, MultipartFile cover) throws Exception {
		System.out.println(id);
		tixing news = tixingService.get(id);
		news.setName(name);
		news.setAddress(content);
		news.setPhone(UploadUtil.fileUpload(cover));
		tixingService.updateById(news);
		return "redirect:newsList?flag=9";
	}

}
