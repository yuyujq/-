package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ItemsDao;
import com.dao.tixingDao;
import com.entity.Goods;
import com.entity.Items;
import com.entity.Users;
import com.entity.tixing;
/**
 * yujq
 * 
 */



/**
 * 商品订单服务
 */
@Service	// 注解为service层spring管理bean
@Transactional	// 注解此类所有方法加入spring事务, 具体设置默认
public class tixingService {

	@Autowired
	private tixingDao tixingDao;
	@Autowired
	private ItemsDao itemDao;
	@Autowired
	private GoodService goodService;
	
	
	/**
	 * 创建订单
	 * @param good
	 * @return
	 */
	public tixing add(Goods good) {
		List<Items> itemList = new ArrayList<Items>();
		itemList.add(addItem(good));
		tixing tixing = new tixing();
		tixing.setItemList(itemList);
		tixing.setTotal(good.getPrice());
		tixing.setAmount(1);
		return tixing;
	}

	/**
	 * 向订单添加项目
	 * @param tixing
	 * @param good
	 * @return
	 */
	public tixing addOrderItem(tixing tixing, Goods good) {
		List<Items> itemList = tixing.getItemList();
		itemList = itemList==null ? new ArrayList<Items>() : itemList;
		// 如果购物车已有此项目, 数量+1
		boolean notThis = true;
		for (Items item : itemList) {
			if (item.getGoodId() == good.getId()) {
				item.setAmount(item.getAmount() + 1);
				item.setTotal(good.getPrice() * item.getAmount());
				notThis = false;
			}
		}
		// 如果当前购物车没有此项目, 创建新条目
		if (notThis) {
			itemList.add(addItem(good));
		}
		tixing.setTotal(tixing.getTotal() + good.getPrice());
		tixing.setAmount(tixing.getAmount() + 1);
		return tixing;
	}
	
	/**
	 * 从订单中减少项目
	 * @param tixing
	 * @param good
	 * @return
	 */
	public tixing lessenIndentItem(tixing tixing, Goods good) {
		List<Items> itemList = tixing.getItemList();
		itemList = itemList==null ? new ArrayList<Items>() : itemList;
		// 如果购物车已有此项目, 数量-1
		boolean noneThis = true;
		for (Items item : itemList) {
			if (item.getGoodId() == good.getId()) {
				if (item.getAmount() - 1 <= 0) { // 减少到0后删除
					return deleteIndentItem(tixing, good);
				}
				item.setAmount(item.getAmount() - 1);
				item.setTotal(good.getPrice() * item.getAmount());
				noneThis = false;
			}
		}
		// 如果当前购物车没有项目, 直接返回
		if (noneThis) {
			return tixing;
		}
		tixing.setTotal(tixing.getTotal() - good.getPrice());
		tixing.setAmount(tixing.getAmount() - 1);
		return tixing;
	}
	
	/**
	 * 从订单中删除项目
	 * @param tixing
	 * @param good
	 * @return
	 */
	public tixing deleteIndentItem(tixing tixing, Goods good) {
		List<Items> itemList = tixing.getItemList();
		itemList = itemList==null ? new ArrayList<Items>() : itemList;
		// 如果购物车已有此项目, 数量清零
		boolean noneThis = true;
		int itemAmount = 0;
		List<Items> resultList = new ArrayList<Items>();
		for (Items item : itemList) {
			if (item.getGoodId() == good.getId()) {
				itemAmount = item.getAmount();
				noneThis = false;
				continue;
			}
			resultList.add(item);
		}
		// 如果已经没有项目, 返回null
		if (resultList.isEmpty()) {
			return null;
		}
		tixing.setItemList(resultList);
		// 如果当前购物车没有项目, 直接返回
		if (noneThis) {
			return tixing;
		}
		tixing.setTotal(tixing.getTotal() - good.getPrice() * itemAmount);
		tixing.setAmount(tixing.getAmount() - itemAmount);
		return tixing;
	}

	
	/**
	 * 获取订单列表
	 * @param page
	 * @param row
	 * @return
	 */
	public List<tixing> getListVgoods(byte status, int page, int row) {
		List<tixing> tixingList = tixingDao.getListByStatusVgoods(status, row * (page-1), row);
		for(tixing tixing : tixingList) {
			tixing.setItemList(this.getItemList(tixing.getId()));
		}
		return tixingList;
	}
	
	/**
	 * 保存订单
	 * @param tixing
	 */
	public int save(tixing tixing) {
		tixing.setStatus(tixing.STATUS_UNPAY);
		tixing.setSystime(new Date());
		tixingDao.insert(tixing);
	 
		return 1;
	}
	
	/**
	 * 订单支付
	 * @param tixing
	 */
	public void pay(tixing tixing) {
		tixing old = tixingDao.selectById(tixing.getId());
		// 微信或支付宝支付时, 模拟支付完成
		int paytype = tixing.getPaytype();
		if(paytype == tixing.PAYTYPE_WECHAT || paytype == tixing.PAYTYPE_ALIPAY) {
			old.setStatus(tixing.STATUS_PAYED);
		}else {
			old.setStatus(tixing.STATUS_SEND);
		}
		old.setPaytype(tixing.getPaytype());
		old.setName(tixing.getName());
		old.setPhone(tixing.getPhone());
		old.setAddress(tixing.getAddress());
		tixingDao.updateById(old);
	}
	
	/**
	 * 获取订单列表
	 * @param page
	 * @param row
	 * @return
	 */
	public List<tixing> getList(byte status, int page, int row) {
		List<tixing> tixingList = tixingDao.getListByStatus(status, row * (page-1), row);
		for(tixing tixing : tixingList) {
			tixing.setItemList(this.getItemList(tixing.getId()));
		}
		return tixingList;
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public int getTotal(byte status) {
		return (int)tixingDao.getTotalByStatus(status);
	}

	/**
	 * 订单发货
	 * @param id
	 * @return 
	 */
	public boolean dispose(int id) {
		tixing tixing = tixingDao.selectById(id);
		tixing.setStatus(tixing.STATUS_SEND);
		return tixingDao.updateByIdSelective(tixing) > 0;
	}
	
	/**
	 * 订单完成
	 * @param id
	 * @return 
	 */
	public boolean finish(int id) {
		tixing tixing = tixingDao.selectById(id);
		tixing.setStatus(tixing.STATUS_FINISH);
		return tixingDao.updateByIdSelective(tixing) > 0;
	}

	/**
	 * 删除订单
	 * @param id
	 */
	public boolean delete(int id) {
		return tixingDao.deleteById(id) > 0;
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(tixing user) {
		return tixingDao.deleteById(user.getId()) > 0;
	}
	
	/**
	 * 获取某用户全部订单
	 * @param userid
	 */
	public List<tixing> getListByUserid(int userid) {
		return tixingDao.getListByUserid(userid);
	}
	
	/**
	 * 处理情况获取已处理或未处理的留言总数和留言集合
	 * @param total
	 */
	public long getByTotal(int total) {
		return tixingDao.getTotaltoa(total);
	}
	public List<tixing> getListByTotal(int total,int page,int rows){
		return tixingDao.getListByTotal(total,rows*(page-1),rows);
	}
	/**
	 * 根据id获取已处理或未处理的留言总数和留言集合
	 * @param total
	 */
	public long getTotalAmount1(int amount) {
		return tixingDao.getTotalAmount(amount);
	}
	public List<tixing> getListByAmount(int amount){
		return tixingDao.getListByAmount(amount);
	}
	/**
	 * 获取未处理的留言
	 * @param amount
	 * @return
	 */
	public long getTotalAmountT1(int amount) {
		return tixingDao.getTotalAmountT1(amount);
	}
	public List<tixing> getListByAmount1(int amount,int page,int rows){
		return tixingDao.getListByAmountTotal1(amount,rows*(page-1),rows);
	}
	/**
	 * 获取已处理的留言
	 * @param amount
	 * @return
	 */
	public long getTotalAmountT2(int amount) {
		return tixingDao.getTotalAmountT2(amount);
	}
	public List<tixing> getListByAmount2(int amount,int page,int rows){
		return tixingDao.getListByAmountTotal2(amount,rows*(page-1),rows);
	}
	
	/**
	 * 获取新闻栏
	 * @return
	 */
	public List<tixing> getListByAmount3(){
		return tixingDao.getListByAmountTotal3();
	}
	
	/**
	 * 根据id修改留言
	 * @param total
	 */
	public void updateById(tixing t) {
		 tixingDao.updateById(t);
	}
	
	
	
	/**
	 * 通过id获取
	 * @param tixingid
	 * @return
	 */
	public tixing get(int tixingid) {
		return tixingDao.selectById(tixingid);
	}
	
	
	/**
	 * 创建订单项
	 * @param good
	 * @return
	 */
	private Items addItem(Goods good) {
		Items item = new Items();
		item.setGoodId(good.getId());
		item.setAmount(1);
		item.setPrice(good.getPrice());
		item.setTotal(good.getPrice());
		item.setGood(goodService.get(item.getGoodId()));
		return item;
	}
	
	/**
	 * 获取订单项目列表
	 * @param tixingid
	 * @return
	 */
	public List<Items> getItemList(int tixingid){
		List<Items> itemList = itemDao.getItemList(tixingid);
		for(Items item : itemList) {
			item.setGood(goodService.get(item.getGoodId()));
		}
		return itemList;
	}
}
