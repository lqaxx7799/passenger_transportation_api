package web.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class SalaryStatistic implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	private int numberOfTripAsMain;
	private int numberOfTripAsSup;
	private float salary;
	private String totalLateHours;
	@JsonBackReference
	List<Trip> trips;
	public SalaryStatistic() {
		super();
	}
	public SalaryStatistic(Employee employee, int numberOfTripAsMain, int numberOfTripAsSup, float salary,
			String totalLateHours ,List<Trip> trips) {
		super();
		this.employee = employee;
		this.numberOfTripAsMain = numberOfTripAsMain;
		this.numberOfTripAsSup = numberOfTripAsSup;
		this.salary = salary;
		this.totalLateHours = totalLateHours;
		this.trips = trips;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getNumberOfTripAsMain() {
		return numberOfTripAsMain;
	}
	public void setNumberOfTripAsMain(int numberOfTripAsMain) {
		this.numberOfTripAsMain = numberOfTripAsMain;
	}
	public int getNumberOfTripAsSup() {
		return numberOfTripAsSup;
	}
	public void setNumberOfTripAsSup(int numberOfTripAsSup) {
		this.numberOfTripAsSup = numberOfTripAsSup;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	public String getTotalLateHours() {
		return totalLateHours;
	}
	public void setTotalLateHours(String totalLateHours) {
		this.totalLateHours = totalLateHours;
	}
}
