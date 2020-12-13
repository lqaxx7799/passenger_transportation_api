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
@CrossOrigin(origins = "*")
public class CoachRevenueStatisticController {
	@Autowired
	private TripRepository tripRepository;

	@GetMapping("/statisticCoachRevenue/{fromDate}/{toDate}")
	public ArrayList<CoachRevenueStatistic> statistic(@PathVariable String fromDate, @PathVariable String toDate)throws ParseException {
		ArrayList<CoachRevenueStatistic> listCoachStatistic = null;

		Date fromDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromDate + " 00:00:00");
		Date toDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toDate + " 00:00:00");

		List<Trip> listTrip = tripRepository.getAllTripFromDateToDate(fromDate1, toDate1);

		ArrayList<Coach> listCoach = new ArrayList<>();

		listCoachStatistic = new ArrayList<>();

		for (Trip trip : listTrip) {
			if (!listCoach.contains(trip.getCoach())) {
				listCoach.add(trip.getCoach());
			}
		}

		for (Coach coach : listCoach) {
			CoachRevenueStatistic crs = new CoachRevenueStatistic(coach, (long) 0);
			List<Trip> t = new ArrayList<>();
			for (Trip trip : listTrip) {
				if (trip.getCoach().equals(coach)) {
					long revenue = (long) (crs.getRevenue() + trip.getNumberOfPassengers() * trip.getTicketPrice());
					crs.setRevenue(revenue);
					t.add(trip);
				}
			}
			crs.setListTrip(t);
			listCoachStatistic.add(crs);
		}
		return listCoachStatistic;
	}
}
