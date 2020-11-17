package web.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.Account;


public interface AccountRepository extends JpaRepository <Account, Integer> {
	@Query("SELECT a FROM Account a WHERE a.isDeleted = false")
	List<Account> findAllAvailable();
	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Account a WHERE a.userName = :username AND a.isDeleted = false")
	boolean existByUserName(@Param("username") String username);
	@Query("SELECT a FROM Account a WHERE a.userName = :userName")
	public Account findByUserName(@Param(value = "userName") String userName);
}
