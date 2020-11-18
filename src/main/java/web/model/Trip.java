package web.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

/**
 * The persistent class for the trips database table.
 * 
 */
@Entity
@Table(name = "trips")
@NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t")
public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrival_time")
	private Date arrivalTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "departure_time")
	private Date departureTime;

	@Column(name = "is_deleted", columnDefinition = "BIT(1)")
	private boolean isDeleted;

	@Column(name = "number_of_passengers")
	private int numberOfPassengers;

	private int status;

	@Column(name = "ticket_price")
	private float ticketPrice;

	// bi-directional many-to-one association to Coach
	@JsonBackReference
	@ManyToOne
		@JoinColumn(name = "coach_id")
		private Coach coach;

	// bi-directional many-to-one association to Employee
	@JsonBackReference
		@ManyToOne
		@JoinColumn(name = "driver_id")
		private Employee employee1;

	// bi-directional many-to-one association to Employee
	@JsonBackReference
		@ManyToOne
		@JoinColumn(name = "assistant_id")
		private Employee employee2;

	// bi-directional many-to-one association to Route
	@JsonBackReference
		@ManyToOne
		@JoinColumn(name = "route_id")
		private Route route;

	public Trip() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getDepartureTime() {
		return this.departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getNumberOfPassengers() {
		return this.numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getTicketPrice() {
		return this.ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Coach getCoach() {
		return this.coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public Employee getEmployee1() {
		return this.employee1;
	}

	public void setEmployee1(Employee employee1) {
		this.employee1 = employee1;
	}

	public Employee getEmployee2() {
		return this.employee2;
	}

	public void setEmployee2(Employee employee2) {
		this.employee2 = employee2;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}