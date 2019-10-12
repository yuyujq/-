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
 * 处理员工crud请求（增删改查）
 * @author 火禾
 *
 */
@Controller
public class EmployeeController{
	
	@Autowired
	EmployeeService employeeService;
	
	
	//删除员工 单个批量二合一
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("ids")String ids) {
		//批量删除和单个删除
		if(ids.contains("-")) {
			String[] str_ids = ids.split("-");
			//组装id集合
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
	
	//保存员工更新信息
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	
	//查询员工信息
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id) {
		Employee employee = employeeService.getEmps(id);
		return Msg.success().add("emp", employee);
	}
	
	
	//检查用户名是否可用
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName) {
		//先判断用户名是否是合法的表达式；
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名可以是2-5位中文或6-16位英文，数字的组合！");
		};
		
		//数据库用户名重复校验
		boolean b = employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg","用户名不可用！");
		}
	}
	
	
	//员工保存
	//1.支持jsr303校验
	//2.导入hibernate-validator
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败，应该返回失败，在静态框中显示校验失败的信息
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	
	
	//导入jackson包。
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model) {
		PageHelper.startPage(pn,5);
		//startpage后面紧跟的查询，就是我们的分页查询
		List<Employee> emps = employeeService.getAll();
		
		//用PageInfo对结果进行包装，只需要将pageinfo交给页面
		//封装了详细的分页信息，包括有我们查询出来的数据,连续传入显示的页数
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo",page);
	}
	
	/**
	 * 查询员工数据（分页查询）
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model) {
		//这不是一个分页查询
		//映入pagehelper分页插件
		//再查询之前只需要调用,传入页码，以及分页每页的大小
		PageHelper.startPage(pn,5);
		//startpage后面紧跟的查询，就是我们的分页查询
		List<Employee> emps = employeeService.getAll();
		
		//用PageInfo对结果进行包装，只需要将pageinfo交给页面
		//封装了详细的分页信息，包括有我们查询出来的数据,连续传入显示的页数
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
	
		//测试PageInfo全部属性
		//PageInfo包含了非常全面的分页属性
		return "list";
	}
}
