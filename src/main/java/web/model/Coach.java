package web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "coaches")
@NamedQuery(name = "Coach.findAll", query = "SELECT c FROM Coach c")
public class Coach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private int id;

	@Column(name = "coach_model")
	private String coachModel;

	@Column(name = "coach_version")
	private String coachVersion;

	private String color;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name = "is_deleted", columnDefinition = "BIT(1)")
	private boolean isDeleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_maintained_date")
	private Date lastMaintainedDate;

	@Column(name = "license_plate")
	private String licensePlate;

	@Column(name = "manufacturer_name")
	private String manufacturerName;

	@Column(name = "number_of_seats")
	private int numberOfSeats;

	@Column(name = "used_years")
	private int usedYears;

	// bi-directional many-to-one association to Trip
	@OneToMany(mappedBy = "coach")
	private List<Trip> trips;

	public Coach() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoachModel() {
		return this.coachModel;
	}

	public void setCoachModel(String coachModel) {
		this.coachModel = coachModel;
	}

	public String getCoachVersion() {
		return this.coachVersion;
	}

	public void setCoachVersion(String coachVersion) {
		this.coachVersion = coachVersion;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getLastMaintainedDate() {
		return this.lastMaintainedDate;
	}

	public void setLastMaintainedDate(Date lastMaintainedDate) {
		this.lastMaintainedDate = lastMaintainedDate;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getManufacturerName() {
		return this.manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int getUsedYears() {
		return this.usedYears;
	}

	public void setUsedYears(int usedYears) {
		this.usedYears = usedYears;
	}

	public List<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Trip addTrip(Trip trip) {
		getTrips().add(trip);
		trip.setCoach(this);

		return trip;
	}

	public Trip removeTrip(Trip trip) {
		getTrips().remove(trip);
		trip.setCoach(null);

		return trip;
	}

}