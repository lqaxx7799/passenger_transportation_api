package web.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.model.Coach;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
	@Query("SELECT c FROM Coach c WHERE c.isDeleted	= false")
	List<Coach> findAllAvailable();	
}
