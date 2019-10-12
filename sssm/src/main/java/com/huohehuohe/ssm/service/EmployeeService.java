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
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null); 
	}
	
	/**
	 * Ա������
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}
	
	/**
	 * �����û����Ƿ����
	 * @param empName
	 * @return true:��ǰ�������� ��false����ǰ����������
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}


	/**
	 * ����Ա��id��ѯԱ��
	 * @param id
	 * @return
	 */
	public Employee getEmps(Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	
	/**
	 * Ա��ɾ��
	 * @param id
	 */
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * Ա�����·���
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	/**
	 * ɾ�����Ա��
	 * @param str_ids
	 */
	public void deleteBatch(List<Integer> str_ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(str_ids);
		employeeMapper.deleteByExample(example);
	}
	

}
