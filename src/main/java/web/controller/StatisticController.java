package web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import web.repos.TripRepository;
import web.model.Employee;
import web.model.SalaryStatistic;
import web.model.Trip;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StatisticController {
	@Autowired
	private TripRepository tripRepository;
	
	@GetMapping("/salary_statistic")
	public List<SalaryStatistic> getSalarytStatistic() throws ParseException {
		List<SalaryStatistic> list = new ArrayList<>();
		List<Employee> employees = new ArrayList<>();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fromDate = LocalDate.now().withDayOfMonth(1).toString() + " 00:00:00";
		System.out.println(sf.parse(fromDate));
		System.out.println(new Date());
		System.out.println(tripRepository.getOne(6).getArrivalTime());
		List<Trip> trips = tripRepository.findFinishTripBetweenDate(sf.parse(fromDate), new Date());
		for(Trip trip : trips) {
			if(!employees.contains(trip.getEmployee1()))
				employees.add(trip.getEmployee1());
			if(!employees.contains(trip.getEmployee2()))
				employees.add(trip.getEmployee2());
		}
		for(Employee employee : employees) {
			SalaryStatistic ss = new SalaryStatistic(employee,0,0,0);
			for(Trip trip : trips) {
				if(trip.getEmployee1().equals(employee)) {
					ss.setNumberOfTripAsMain(ss.getNumberOfTripAsMain() + 1);
					ss.setSalary(ss.getSalary() + 100000*trip.getRoute().getRouteComplexity()*2);
				}
				else if(trip.getEmployee2().equals(employee)) {
					ss.setNumberOfTripAsSup(ss.getNumberOfTripAsSup() + 1);
					ss.setSalary(ss.getSalary() + 100000*trip.getRoute().getRouteComplexity());
				}
			}
			list.add(ss);
		}
		return list;
	}
}
