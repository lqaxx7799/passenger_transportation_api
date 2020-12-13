package web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/salary_statistic/{year}/{month}")
	public List<SalaryStatistic> getSalarytStatistic(@PathVariable int year ,@PathVariable int month) 
			throws ParseException {
		List<SalaryStatistic> list = new ArrayList<>();
		List<Employee> employees = new ArrayList<>();
		List<Trip> trips = new ArrayList<>();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fromDate = null;
		String toDate = null;
		if(year == LocalDate.now().getYear() && month == LocalDate.now().getMonthValue()){
		    fromDate = LocalDate.now().withDayOfMonth(1).toString() + " 00:00:00";
			trips = tripRepository.findFinishTripBetweenDate(sf.parse(fromDate), new Date());
		}
		else {
			YearMonth yearMonth = YearMonth.of(year,month);
			fromDate = yearMonth.atDay(1).toString() + " 00:00:00";
			toDate = yearMonth.atEndOfMonth().toString() + " 23:59:59";
			trips = tripRepository.findFinishTripBetweenDate(sf.parse(fromDate), sf.parse(toDate));
		}
		for(Trip trip : trips) {
			if(!employees.contains(trip.getEmployee1()))
				employees.add(trip.getEmployee1());
			if(!employees.contains(trip.getEmployee2()))
				employees.add(trip.getEmployee2());
		}
		for(Employee employee : employees) {
			SalaryStatistic ss = new SalaryStatistic(employee,0,0,0,"0h0",new ArrayList<Trip>());
			for(Trip trip : trips) {
				if(trip.getEmployee1().equals(employee)){
					ss.setNumberOfTripAsMain(ss.getNumberOfTripAsMain() + 1);
					ss.setSalary(ss.getSalary() + 100000*trip.getRoute().getRouteComplexity()*2);
					
					long dif = trip.getArrivalTime().getTime() - trip.getDepartureTime().getTime();
					
					String[] str = ss.getTotalLateHours().split("h");
					
					long h = dif / (60 * 60 * 1000);
					long m = dif / (60*1000) % 60 ;
					if(h>trip.getRoute().getEstimatedHours()) {
						float x = (float)h  - trip.getRoute().getEstimatedHours();
						h = (long)x + Long.parseLong(str[0]);
						m = m + (long) ((x%1)*60) + Long.parseLong(str[1]);
						if(m>=60) {
							m = m%60;
							h += (long) m/60;
						}
						
						ss.setTotalLateHours(h+"h"+m);
					}
					List<Trip> t = ss.getTrips();
					t.add(trip);
					ss.setTrips(t);
				}
				else if(trip.getEmployee2().equals(employee)) {
					ss.setNumberOfTripAsSup(ss.getNumberOfTripAsSup() + 1);
					ss.setSalary(ss.getSalary() + 100000*trip.getRoute().getRouteComplexity());
					
					long dif = trip.getArrivalTime().getTime() - trip.getDepartureTime().getTime();
					
					String[] str = ss.getTotalLateHours().split("h");
					
					long h = dif / (60 * 60 * 1000);
					long m = dif / (60*1000) % 60 ;
					if(h>trip.getRoute().getEstimatedHours()) {
						float x = (float)h  - trip.getRoute().getEstimatedHours();
						h = (long)x + Long.parseLong(str[0]);
						m = m + (long) ((x%1)*60) + Long.parseLong(str[1]);
						if(m>=60) {
							m = m%60;
							h += (long) m/60;
						}
						
						ss.setTotalLateHours(h+"h"+m);
					}
					
					List<Trip> t = ss.getTrips();
					t.add(trip);
					ss.setTrips(t);
				}
			}
			int totalLateHours = Integer.parseInt(ss.getTotalLateHours()
					.substring(0,ss.getTotalLateHours().lastIndexOf("h")));
			if( totalLateHours >10) {
				ss.setSalary(ss.getSalary() - (totalLateHours-10)*100000);
			}
			list.add(ss);
		}
		Collections.sort(list, new Comparator<SalaryStatistic>() {
		    @Override
		    public int compare(SalaryStatistic s1, SalaryStatistic s2) {
		        return (int)(s2.getSalary() - s1.getSalary());
		    }
		});
		
		return list;
	}
	
//	@GetMapping("/salary_statistic/detail/{id}")
//	public List<Trip> getDetail(@PathVariable int id){
//		List<Trip> trip= new ArrayList<>();
//		for(SalaryStatistic ss : list) {
//			if(ss.getEmployee().getId() == id) {
//				trip = ss.getTrips();
//				break;
//			}
//		}
//		return trip;
//	}
}
