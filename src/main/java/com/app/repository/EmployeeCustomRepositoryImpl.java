package com.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.app.model.Employee;

@Repository
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Employee> getAllEmployees() {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			criteriaQuery.select(root);
			
			TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
			
			List<Employee> employeeList = typedQuery.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Employee getEmployeeByEmployeeId(Integer employeeId) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			criteriaQuery.where(criteriaBuilder.equal(root.get("employeeId"), employeeId));
			
			TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
			
			List<Employee> employeeList = typedQuery.getResultList();
			
			Employee employee = null;
			if(employeeList != null && !employeeList.isEmpty()) {
				employee = employeeList.get(0);
			}
			
			return employee;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public List<Employee> getEmployeeByEmployeeCodeAndEmployeeName(String employeeCode, String employeeName) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Predicate employeeCodePredicate = criteriaBuilder.equal(root.get("employeeCode"), employeeCode);
			Predicate employeeNamePredicate = criteriaBuilder.equal(root.get("employeeName"), employeeName);
			
			criteriaQuery.where(employeeCodePredicate, employeeNamePredicate);
			
			TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
			
			List<Employee> employeeList = typedQuery.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public List<Employee> getEmployeeByEmployeeCodeOrEmployeeName(String employeeCode, String employeeName) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Predicate employeeCodePredicate = criteriaBuilder.equal(root.get("employeeCode"), employeeCode);
			Predicate employeeNamePredicate = criteriaBuilder.equal(root.get("employeeName"), employeeName);
			
			criteriaQuery.where(criteriaBuilder.or(employeeCodePredicate, employeeNamePredicate));
			
			TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
			
			List<Employee> employeeList = typedQuery.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employee> getEmployeesByDesignation(String designation) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Predicate destinationPredicate = criteriaBuilder.equal(root.get("designation"), designation);
			
			criteriaQuery.where(destinationPredicate);
			
			TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
			
			List<Employee> employeeList = typedQuery.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employee> getEmployeesByEmployeeIds(List<Integer> employeeIds) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Predicate employeeIdPredicate = root.get("employeeId").in(employeeIds);
			
			criteriaQuery.where(employeeIdPredicate);
			
			TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
			
			List<Employee> employeeList = typedQuery.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<String> getEmployeeNamesOrderBy(String orderBy) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			criteriaQuery.select(root.get("employeeName"));
			
			if(orderBy != null && orderBy.equalsIgnoreCase("desc")) {
				criteriaBuilder.desc(root.get("employeeName"));
			}else if(orderBy != null && orderBy.equalsIgnoreCase("asc")) {
				criteriaBuilder.asc(root.get("employeeName"));
			}
			
			TypedQuery<String> typedQuery = entityManager.createQuery(criteriaQuery);
			
			List<String> employeeList = typedQuery.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employee> getEmployees(Integer firstResult, Integer maxResult) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			criteriaQuery.select(root);
			
			TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
			
			typedQuery.setFirstResult(firstResult);
			typedQuery.setMaxResults(maxResult);
			
			List<Employee> employeeList = typedQuery.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long getEmployeeCount() {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			criteriaQuery.select(criteriaBuilder.count(root));
			
			TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
			
			Long employeeCount = typedQuery.getSingleResult();
			
			return employeeCount;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Double getEmployeeAverageAge() {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			criteriaQuery.select(criteriaBuilder.avg(root.get("age")));
			
			TypedQuery<Double> typedQuery = entityManager.createQuery(criteriaQuery);
			
			Double averageEmployeeAge = typedQuery.getSingleResult();
			
			return averageEmployeeAge;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Employee> getMinimumAgeEmployee() {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
			Root<Employee> root1 = criteriaQuery.from(Employee.class);
			
			Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
			Root<Employee> root2 = subquery.from(Employee.class);
			
			subquery.select(criteriaBuilder.min(root2.get("age")));
			
			criteriaQuery.where(criteriaBuilder.equal(root1.get("age"), subquery));
			
			TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
			
			List<Employee> employeeList = typedQuery.getResultList();
			
			return employeeList;
		} catch (Exception e) {
			throw e;
		}
	}

}
