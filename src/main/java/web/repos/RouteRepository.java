package web.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.model.Route;

public interface RouteRepository extends JpaRepository<Route, Integer>{
	@Query("SELECT r FROM Route r WHERE r.isDeleted	= false")
	List<Route> findAllAvailable();
}
