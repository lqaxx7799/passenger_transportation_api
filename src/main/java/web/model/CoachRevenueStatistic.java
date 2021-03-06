package web.model;

import java.io.Serializable;
import java.util.List;

public class CoachRevenueStatistic implements Serializable{
	private static final long serialVersionUID = 1L;
	private Coach coach;
	private long revenue;
	private List<Trip> listTrip;
	
	public CoachRevenueStatistic() {
		super();
	}
	
	public CoachRevenueStatistic(Coach coach, long revenue) {
		super();
		this.coach = coach;
		this.revenue = revenue;
	}

	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public long getRevenue() {
		return revenue;
	}
	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}

	public List<Trip> getListTrip() {
		return listTrip;
	}

	public void setListTrip(List<Trip> listTrip) {
		this.listTrip = listTrip;
	}
	
}
