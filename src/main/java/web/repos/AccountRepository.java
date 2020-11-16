package web.repos;

<<<<<<< HEAD
import java.util.List;

=======
import org.hibernate.annotations.Parameter;
>>>>>>> 02284d25bbc31070c33c1b0e1fa7d1bd46377463
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.Account;

<<<<<<< HEAD
public interface AccountRepository extends JpaRepository <Account, Integer> {
	@Query("SELECT a FROM Account a WHERE a.isDeleted = false")
	List<Account> findAllAvailable();
	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Account a WHERE a.userName = :username AND a.isDeleted = false")
	boolean existByUserName(@Param("username") String username);
=======
public interface AccountRepository  extends JpaRepository<Account, Integer> {
	@Query("SELECT a FROM Account a WHERE a.userName = :userName")
	public Account findByUserName(@Param(value = "userName") String userName);
>>>>>>> 02284d25bbc31070c33c1b0e1fa7d1bd46377463
}
