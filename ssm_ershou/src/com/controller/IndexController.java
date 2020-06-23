package com.controller;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
/**
 * yujq
 * 
 */
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Goods;
import com.entity.Tops;
import com.entity.Types;
import com.entity.tixing;
import com.service.GoodService;
import com.service.TopService;
import com.service.TypeService;
import com.service.tixingService;
import com.sun.javafx.collections.MappingChange.Map;
import com.util.PageUtil;
import com.util.SmsUtil;

/**
 * 前台相关接口
 */
@Controller
@RequestMapping("/index")
public class IndexController{
	
	private static final int rows = 16; // 默认每页数量
	

	@Autowired
	private TopService topService;
	@Autowired
	private GoodService goodService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private tixingService tixingService;
	

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		request.setAttribute("flag", 1);
		request.setAttribute("newsList1", tixingService.getListByAmount3().get(0));
		request.setAttribute("newsList2", tixingService.getListByAmount3().get(1));
		request.setAttribute("newsList3", tixingService.getListByAmount3().get(2));
		request.setAttribute("newsList4", tixingService.getListByAmount3().get(3));
		request.setAttribute("typeList", typeService.getList());
		request.setAttribute("top1List", topService.getList(Tops.TYPE_SCROLL, 1, 10));
		request.setAttribute("top2List", topService.getList(Tops.TYPE_LARGE, 1, 6));
		request.setAttribute("top3List", topService.getList(Tops.TYPE_SMALL, 1, 8));
		return "/index/index.jsp";
	}
	/**
	 * 查看新闻
	 */
	@RequestMapping("newsop")
	public String newsOpen(int id,HttpServletRequest request) {
		request.setAttribute("news", tixingService.get(id));
		return "/index/newsOpen.jsp";
		
	}
	
	/**
	 * 推荐列表
	 * @return
	 */
	@RequestMapping("/top")
	public String tops(int typeid, @RequestParam(required=false, defaultValue="1")int page, HttpServletRequest request) {
		request.setAttribute("flag", typeid==2 ? 7 : 8);
		request.setAttribute("typeList", typeService.getList());
		request.setAttribute("goodList", goodService.getList(typeid, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotal(typeid), page, rows));
		return "/index/goods.jsp";
	}
	
	/**
	 * 商品列表
	 * @return
	 */
	@RequestMapping("/goods")
	public String goods(int typeid, @RequestParam(required=false, defaultValue="1")int page, HttpServletRequest request){
		request.setAttribute("flag", 2);
		if (typeid > 0) {
			request.setAttribute("type", typeService.get(typeid));
		}
		request.setAttribute("typeList", typeService.getList());
		if(goodService.getListByType(typeid, page, rows)!=null&&!goodService.getListByType(typeid, page, rows).isEmpty()) {
		request.setAttribute("goodList", goodService.getListByType(typeid, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotalByType(typeid), page, rows));}
		else {
			request.setAttribute("Npage", 1);
		}
		return "/index/goods.jsp";
	}
	

	
	/**
	 * 提交留言
	 * @return
	 */	
	@RequestMapping("/aj")
	public void confrim1(String pwd,int id,HttpServletResponse response) throws IOException {
		System.out.println(pwd+"  "+id);
		if(pwd!=null) {
			tixing tx = new tixing();
			tx.setTotal(1);//用1表示该问题为留言，2表示已答复留言！
			tx.setAmount(id);//把商品编号插入Amount属性
			tx.setName(pwd);//把问题插入Name属性
			tixingService.save(tx);
			response.getWriter().print(true);
		}else {
			response.getWriter().print(false);			
		}
		}
	
//	@RequestMapping("/mmsg/{id}")
//	public void confrim2(@PathVariable("id") int id){
//		System.out.println(id);
//		}
	/**
	 * 查看相关留言
	 * @return
	 */	
	@RequestMapping("/mmsg")
	public String confrim1(int id,@RequestParam(required=false, defaultValue="1")int page, HttpServletRequest request){
		request.setAttribute("tixingList", tixingService.getListByAmount2(id,page,rows));
		List<tixing> list = tixingService.getListByAmount2(id,page,rows);
		if(list.size()==0) {
			request.setAttribute("flag",0);
		}
		request.setAttribute("pageTool", PageUtil.getPageTool(request, tixingService.getTotalAmountT2(id), page, rows));
		return "/index/msg.jsp";
		}
		
//	@RequestMapping("/msg/{id}")
//	public String confrim(@PathVariable("id") Integer id,String program,Model model,RedirectAttributes attr) {
//		System.out.println(id+program);
//		tixing tx = new tixing();
//		tx.setTotal(1);//用1表示该问题为提醒
//		tx.setAmount(id);//把商品编号插入Amount属性
//		tx.setName(program);//把问题插入Name属性
//		tixingService.save(tx);
//		model.addAttribute("msg","提交成功！");
//		int id1 = id.intValue();
//		attr.addFlashAttribute("goodid", id1);
//		return "redirect:/index/detail";
//		}
		
	/**
	 * 商品详情
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(int goodid, HttpServletRequest request){
		request.setAttribute("good", goodService.get(goodid));
		request.setAttribute("typeList", typeService.getList());
		return "/index/detail.jsp";
	}
	
	/**
	 * 搜索
	 * @return
	 */
	@RequestMapping("/search")
	public String search(String name, @RequestParam(required=false, defaultValue="1")int page, HttpServletRequest request) {
		if (Objects.nonNull(name) && !name.trim().isEmpty()) {
			if(goodService.getListByName(name, page, rows)!=null&&!goodService.getListByName(name, page, rows).isEmpty()) {
			request.setAttribute("sel", 0);
			request.setAttribute("selname", name);
			request.setAttribute("goodList", goodService.getListByName(name, page, rows));
			request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotalByName(name), page, rows));}
			else {
				request.setAttribute("Npage", 1);
			}
		}
		request.setAttribute("typeList", typeService.getList());
		return "/index/goods.jsp";
	}
	
	/**
	 * 按价格查询！
	 * @param lprice
	 * @param hprice
	 * @param id
	 * @param temp
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping("priceSel")
	public String searchPrc(int lprice,int hprice,int id,int temp,@RequestParam(required=false, defaultValue="1")int page,
			HttpServletRequest request) {
		int typeid = 0;
		if(temp==1) {
			request.setAttribute("flag", 2);
			request.setAttribute("typeList", typeService.getList());
			request.setAttribute("type", typeService.get(id));
			if(goodService.getListByType(id, page, rows, lprice, hprice)!=null&&!goodService.getListByType(id, page, rows, lprice, hprice).isEmpty()) {
			request.setAttribute("goodList", goodService.getListByType(id, page, rows, lprice, hprice));
			request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotalByType(id, lprice, hprice), page, rows));}
			else {
				request.setAttribute("Npage", 1);
			}
			return "/index/goods.jsp";
		}
		else if(temp==2) {
			typeid = id==7 ? 2 : 3;
			request.setAttribute("flag", id);
			request.setAttribute("typeList", typeService.getList());
			if(goodService.getList(typeid, page, rows,lprice, hprice)!=null&&!goodService.getList(typeid, page, rows,lprice, hprice).isEmpty()) {
			request.setAttribute("goodList", goodService.getList(typeid, page, rows,lprice, hprice));
			request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getList(typeid, page, rows,lprice, hprice).size(), page, rows));}
			else {
				request.setAttribute("Npage", 1);
			}
			return "/index/goods.jsp";
		}
		
		
		return "/index/goods.jsp";
		
	}

}