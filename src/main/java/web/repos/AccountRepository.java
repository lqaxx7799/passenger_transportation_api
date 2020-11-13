package web.repos;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.Account;

public interface AccountRepository  extends JpaRepository<Account, Integer> {
	@Query("SELECT a FROM Account a WHERE a.userName = :userName")
//	@Parameter(name = "userName", value = "userName")
	public Account findByUserName(String userName);
}
