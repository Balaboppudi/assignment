package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.exception.EmployeeNotFoundException;
import com.assignment.model.Employee;
import com.assignment.services.EmployeeService;


@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	
	@RequestMapping(value="/getEmployee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object editEmployee(@PathVariable("id") int id){
       try{
    	  return this.employeeService.getEmployeeById(id);
       }catch(EmployeeNotFoundException e){
    	   return "notFound";
       }
    }
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> listEmployees() {
		return this.employeeService.listEmployees();
	}
	
	@RequestMapping(value= "/employee/add", method = RequestMethod.POST)
	@ResponseBody
	public Boolean addEmployee(@RequestBody Employee p){
		try{
			this.employeeService.addEmployee(p);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	@RequestMapping(value= "/employee/update", method = RequestMethod.POST)
	@ResponseBody
	public Boolean updateEmployee(@RequestBody Employee p){
		try{
			this.employeeService.updateEmployee(p);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@RequestMapping("/remove-employee/{id}")
	@ResponseBody
	public String removeEmployee(@PathVariable("id") int id){
		
        this.employeeService.removeEmployee(id);
        return "updated";
    }
 
	
}