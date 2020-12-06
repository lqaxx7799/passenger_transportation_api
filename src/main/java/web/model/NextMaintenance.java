package web.model;

import java.util.Date;

public class NextMaintenance {
	private Coach coach;
	private Date Next;
	public NextMaintenance(Coach coach, Date next) {
		super();
		this.coach = coach;
		this.Next = next;
	}
	public NextMaintenance() {
		super();
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public Date getNext() {
		return Next;
	}
	public void setNext(Date next) {
		Next = next;
	}
	
}
