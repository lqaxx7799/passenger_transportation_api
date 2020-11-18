package web.repos;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer>{
	@Query("SELECT t FROM Trip t WHERE t.isDeleted	= false")
	List<Trip> findAllAvailable();
	
	// 0 - waiting
	// 1 - running
	// 2 - end
	@Query("SELECT t FROM Trip t WHERE t.isDeleted = false AND t.status = ?1")
	List<Trip> findAllByStatus(int status);
	
	@Query("SELECT t FROM Trip t WHERE t.isDeleted = false AND t.arrivalTime > ?1 AND t.arrivalTime < ?2")
	List<Trip> findFinishTripBetweenDate(Date fromDate, Date toDate);

public interface TripRepository extends JpaRepository<Trip, Integer> {
	@Query("SELECT t FROM Trip t WHERE t.departureTime >= ?1 AND t.arrivalTime <= ?2")
	List<Trip> getAllTripFromDateToDate(Date fromDate , Date toDate);
}
