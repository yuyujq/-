package com.huohehuohe.ssm.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huohehuohe.ssm.bean.Department;
import com.huohehuohe.ssm.bean.Employee;
import com.huohehuohe.ssm.dao.DepartmentMapper;
import com.huohehuohe.ssm.dao.EmployeeMapper;

/**
 * ����dao��Ĺ���
 * @author ���
 * �Ƽ�spring����Ŀʹ��spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 * 1.����springtestģ��
 * 2.@ContextConfigurationָ��spring�ļ���λ��
 * 3.ֱ��autowiredҪʹ�õ��������
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	/**
	 * ����departmentmapper
	 */
	
	@Test
	public void testCRUD(){
//		//1.����spring��ioc����
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		//2.�������л�ȡmapper
//		ioc.getBean(DepartmentMapper.class);
		
		System.out.println(departmentMapper);
		
		
		
//		1.���뼸������
//		departmentMapper.insertSelective(new Department(null,"������"));
//		departmentMapper.insertSelective(new Department(null,"���Բ�"));
		
		
		//2.����Ա�����ݣ�
		//employeeMapper.insert(new Employee(1, "voladar", "M", "voladar@stardust.com", 1));
		
		//3.����������Ա����ʹ�ÿ���ִ������������SQLSession
//		for() {
//			employeeMapper.insert(new Employee(1, "we", "f", "132131412", 2, new Department()));
//		}
		
//		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//		for(int i=0;i<1000;i++) {
//			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
//			mapper.insertSelective(new Employee(null, uid, "m", uid+"@stardust.com", 1));
//		}
//		System.out.println("�����ɹ���");
		
	}

}
