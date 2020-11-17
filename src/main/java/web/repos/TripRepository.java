package web.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
	@Query("SELECT t FROM Trip t WHERE t.departureTime >= ?1 AND t.arrivalTime <= ?2")
	List<Trip> getAllTripFromDateToDate(Date fromDate , Date toDate);
}
