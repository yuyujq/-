package com.huohehuohe.ssm.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huohehuohe.ssm.bean.Employee;
import com.huohehuohe.ssm.bean.EmployeeExample;
import com.huohehuohe.ssm.bean.EmployeeExample.Criteria;
import com.huohehuohe.ssm.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null); 
	}
	
	/**
	 * 员工保存
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}
	
	/**
	 * 检验用户名是否可用
	 * @param empName
	 * @return true:当前姓名可用 ，false：当前姓名不可用
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}


	/**
	 * 按照员工id查询员工
	 * @param id
	 * @return
	 */
	public Employee getEmps(Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	
	/**
	 * 员工删除
	 * @param id
	 */
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 员工更新方法
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	/**
	 * 删除多个员工
	 * @param str_ids
	 */
	public void deleteBatch(List<Integer> str_ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(str_ids);
		employeeMapper.deleteByExample(example);
	}
	

}
