package com.huohehuohe.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huohehuohe.ssm.bean.Employee;
import com.huohehuohe.ssm.bean.Msg;
import com.huohehuohe.ssm.service.EmployeeService;

/**
 * ����Ա��crud������ɾ�Ĳ飩
 * @author ���
 *
 */
@Controller
public class EmployeeController{
	
	@Autowired
	EmployeeService employeeService;
	
	
	//ɾ��Ա�� ������������һ
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("ids")String ids) {
		//����ɾ���͵���ɾ��
		if(ids.contains("-")) {
			String[] str_ids = ids.split("-");
			//��װid����
			List<Integer> del_ids = new ArrayList<Integer>();
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt
						(string));
			}
			employeeService.deleteBatch(del_ids);
		}else {
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}
	
	//����Ա��������Ϣ
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	
	//��ѯԱ����Ϣ
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id) {
		Employee employee = employeeService.getEmps(id);
		return Msg.success().add("emp", employee);
	}
	
	
	//����û����Ƿ����
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ��
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "�û���������2-5λ���Ļ�6-16λӢ�ģ����ֵ���ϣ�");
		};
		
		//���ݿ��û����ظ�У��
		boolean b = employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg","�û��������ã�");
		}
	}
	
	
	//Ա������
	//1.֧��jsr303У��
	//2.����hibernate-validator
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ��ھ�̬������ʾУ��ʧ�ܵ���Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError : errors) {
				System.out.println("������ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	
	
	//����jackson����
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model) {
		PageHelper.startPage(pn,5);
		//startpage��������Ĳ�ѯ���������ǵķ�ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
		
		//��PageInfo�Խ�����а�װ��ֻ��Ҫ��pageinfo����ҳ��
		//��װ����ϸ�ķ�ҳ��Ϣ�����������ǲ�ѯ����������,����������ʾ��ҳ��
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo",page);
	}
	
	/**
	 * ��ѯԱ�����ݣ���ҳ��ѯ��
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model) {
		//�ⲻ��һ����ҳ��ѯ
		//ӳ��pagehelper��ҳ���
		//�ٲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ���ҳÿҳ�Ĵ�С
		PageHelper.startPage(pn,5);
		//startpage��������Ĳ�ѯ���������ǵķ�ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
		
		//��PageInfo�Խ�����а�װ��ֻ��Ҫ��pageinfo����ҳ��
		//��װ����ϸ�ķ�ҳ��Ϣ�����������ǲ�ѯ����������,����������ʾ��ҳ��
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
	
		//����PageInfoȫ������
		//PageInfo�����˷ǳ�ȫ��ķ�ҳ����
		return "list";
	}
}
