package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.tixing;
/**
 * yujq
 * 
 */
public interface tixingDao {
    int deleteById(Integer id);

    int insert(tixing record);

    int insertSelective(tixing record);

    tixing selectById(Integer id);

    int updateByIdSelective(tixing record);

    int updateById(tixing record);    
    
    // 以上为mybatis generator自动生成接口, 具体实现在mapper.xml中
    
    // ------------------------------------------------------------
    
    // 以下方法使用mybatis注解实现
    
	/**
	 * 获取列表
	 * @param status
	 * @param page
	 * @param row
	 */
    @Select("select * from tixing order by id desc limit #{begin}, #{size}")
	public List<tixing> getList(@Param("begin")int begin, @Param("size")int size);

	/**
	 * 获取总数
	 * @param status
	 * @return
	 */
    @Select("select count(*) from tixing")
	public long getTotal();
    
    /**
     * 根据处理情况查看留言
     * 1：未处理，2：已处理改为
     * @param total
     * @return
     */
    @Select("select count(*) from tixing where total=#{total}")
    public long getTotaltoa(@Param("total")int total);
    @Select("select * from tixing where total=#{total} order by id desc limit #{begin}, #{size}")
	public List<tixing> getListByTotal(@Param("total")int total,@Param("begin")int begin, @Param("size")int size);
	
	/**
     * 根据商品id查询留言
     * 
     * @param total
     * @return
     */
	//所有总数
    @Select("select count(*) from tixing where amount=#{amount}")
    public long getTotalAmount(@Param("amount")int amount);
    @Select("select * from tixing where amount=#{amount}")
	public List<tixing> getListByAmount(@Param("amount")int amount);
	//
	@Select("select count(*) from tixing where amount=#{amount} and total=1")
	public long getTotalAmountT1(@Param("amount")int amount);
	@Select("select * from tixing where amount=#{amount} and total=1 order by id desc limit #{begin}, #{size}")
	public List<tixing> getListByAmountTotal1(@Param("amount")int amount, @Param("begin")int begin, @Param("size")int size);
	//
	@Select("select count(*) from tixing where amount=#{amount} and total=2")
	public long getTotalAmountT2(@Param("amount")int amount);
	@Select("select * from tixing where amount=#{amount} and total=2 order by id desc limit #{begin}, #{size}")
	public List<tixing> getListByAmountTotal2(@Param("amount")int amount, @Param("begin")int begin, @Param("size")int size);
	
	@Select("select * from tixing where  total=3 ")
	public List<tixing> getListByAmountTotal3();
	
    /**
     * 获取列表
     * @param status
     * @param page
     * @param row
     */
    @Select("select * from tixing where 1=1 and total=0 order by id desc limit #{begin}, #{size}")
    public List<tixing> getListByStatus(@Param("status")byte status, @Param("begin")int begin, @Param("size")int size);
    
    
    
    /**
     * 获取列表View
     * @param status
     * @param page
     * @param row
     */
    @Select("select * from Vgoods where status=#{status} order by id desc limit #{begin}, #{size}")
    public List<tixing> getListByStatusVgoods(@Param("status")byte status, @Param("begin")int begin, @Param("size")int size);
     
    /**
     * 获取总数
     * @param status
     * @return
     */
    @Select("select count(*) from tixing where total = 0")
    public long getTotalByStatus(@Param("status")byte status);

	/**
	 * 通过用户获取列表
	 * @param userid
	 */
    @Select("select * from tixing where user_id=#{userid} order by id desc")
	public List<tixing> getListByUserid(@Param("userid")int userid);

}