package web.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.Route;

public interface RouteRepository extends JpaRepository<Route, Integer>{
	@Query("SELECT r FROM Route r WHERE r.isDeleted	= false")
	List<Route> findAllAvailable();
	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Route r WHERE r.startingPoint = :startingPoint "
			+ "AND r.destinationPoint = :destinationPoint AND r.isDeleted = false")
	boolean existRoute(@Param("startingPoint") String startingPoint
			,@Param("destinationPoint") String destinationPoint);
}
