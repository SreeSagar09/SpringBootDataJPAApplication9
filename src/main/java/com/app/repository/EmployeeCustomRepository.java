package com.app.repository;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeCustomRepository {
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeByEmployeeId(Integer employeeId);
	
	public List<Employee> getEmployeeByEmployeeCodeAndEmployeeName(String employeeCode, String employeeName);
	
	public List<Employee> getEmployeeByEmployeeCodeOrEmployeeName(String employeeCode, String employeeName);
	
	public List<Employee> getEmployeesByDesignation(String designation);
	
	public List<Employee> getEmployeesByEmployeeIds(List<Integer> employeeIds);
	
	public List<String> getEmployeeNamesOrderBy(String orderBy);
	
	public List<Employee> getEmployees(Integer firstResult, Integer maxResult);
	
	public Long getEmployeeCount();
	
	public Double getEmployeeAverageAge();
	
	public List<Employee> getMinimumAgeEmployee();
}
