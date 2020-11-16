package web.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query("SELECT e FROM Employee e WHERE e.isWorking	= true")
	List<Employee> findAllAvailable();
}
