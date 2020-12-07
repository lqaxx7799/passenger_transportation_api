package web.model;

public class OverMaintenance {
	private Coach coach;
	private int overDays;
	public OverMaintenance(Coach coach, int overDays) {
		super();
		this.coach = coach;
		this.overDays = overDays;
	}
	public OverMaintenance() {
		super();
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public int getOverDays() {
		return overDays;
	}
	public void setOverDays(int overDays) {
		this.overDays = overDays;
	}
	
}
