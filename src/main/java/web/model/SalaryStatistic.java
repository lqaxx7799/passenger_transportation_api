package web.model;

import java.io.Serializable;

public class SalaryStatistic implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	private int numberOfTripAsMain;
	private int numberOfTripAsSup;
	private float salary;
	public SalaryStatistic() {
		super();
	}
	public SalaryStatistic(Employee employee, int numberOfTripAsMain, int numberOfTripAsSup, float salary) {
		super();
		this.employee = employee;
		this.numberOfTripAsMain = numberOfTripAsMain;
		this.numberOfTripAsSup = numberOfTripAsSup;
		this.salary = salary;
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
	
}
