package web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import web.repos.TripRepository;
import web.model.Coach;
import web.model.Trip;
import web.model.CoachRevenueStatistic;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CoachRevenueStatisticController {
	@Autowired
	private TripRepository tripRepository;
	
	@GetMapping("/statisticCoachRevenue/{fromDate}/{toDate}")
	public ArrayList<CoachRevenueStatistic> statistic(@PathVariable String fromDate, @PathVariable String toDate) throws ParseException {
		
		Date fromDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromDate+" 00:00:00");
		Date toDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toDate+" 00:00:00");
		
		List<Trip> list = tripRepository.getAllTripFromDateToDate(fromDate1, toDate1);
		
		ArrayList<Coach> list1 = new ArrayList<>();
		
		ArrayList<CoachRevenueStatistic> list2 = new ArrayList<>();
		
		for(Trip trip : list) {
			if(!list1.contains(trip.getCoach())) {
				list1.add(trip.getCoach());
			}
		}
		
		for(Coach coach : list1) {
			CoachRevenueStatistic crs = new CoachRevenueStatistic(coach,(long) 0);
			for(Trip trip : list) {
				if(trip.getCoach().equals(coach)) {
					long revenue = (long) (crs.getRevenue() + trip.getNumberOfPassengers()*trip.getTicketPrice());
					crs.setRevenue(revenue);
				}
			}
			list2.add(crs);
		}
		return list2;
	}
}
