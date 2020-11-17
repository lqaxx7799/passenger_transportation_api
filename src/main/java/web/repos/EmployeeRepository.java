package web.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query("SELECT e FROM Employee e WHERE e.isWorking	= true")
	List<Employee> findAllAvailable();
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.identificationNumber = :identificationNumber AND e.isWorking = true")
	boolean existByIdentificationNumber(@Param("identificationNumber") String identificationNumber);
}
