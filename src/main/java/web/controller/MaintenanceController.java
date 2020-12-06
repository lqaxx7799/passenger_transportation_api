package web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import web.repos.CoachRepository;
import web.repos.TripRepository;
import web.model.NextMaintenance;
import web.model.OverMaintenance;
import web.model.Trip;
import web.model.Coach;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceController {
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private TripRepository tripRepository;
	
	@GetMapping("/nextMaintenance")
	public List[] getNextMaintenance(){
		Calendar cal = Calendar.getInstance();
		List<NextMaintenance> nextlist = new ArrayList<>();
		List<OverMaintenance> overlist = new ArrayList<>();
		List<Coach> coachs = coachRepository.findAllAvailable();
		for(Coach coach : coachs) {
			List<Trip> trips = tripRepository.findFinishTripBetweenDateByCoachId(coach.getLastMaintainedDate(), 
					new Date(),coach);
			float totalKm = 0;
			for(Trip trip : trips) {
				totalKm += trip.getRoute().getDistance()*trip.getRoute().getRouteComplexity();
			}
			int remainingDays = 360 - (int)(totalKm/100);
			cal.setTime(coach.getLastMaintainedDate());
			cal.add(Calendar.DATE, remainingDays);
			if(cal.getTime().after(new Date())) {
				NextMaintenance nextM = new NextMaintenance(coach,cal.getTime());
				nextlist.add(nextM);
			}
			else {
				OverMaintenance over = new OverMaintenance(coach,
						(int)((new Date().getTime() - cal.getTime().getTime())/(1000*60*60*24)));
				overlist.add(over);
			}
		}
		return new List[] {nextlist,overlist};
	}
}
