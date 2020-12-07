package web.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the routes database table.
 * 
 */
@Entity
@Table(name = "routes")
@NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Date createdTime;
	
	@NotEmpty(message = "Please provide a destination point")
	@Column(name = "destination_point")
	private String destinationPoint;
	
	@NotNull(message = "please provide a distance")
	@DecimalMin("1.00")
	private float distance;

	@NotNull(message = "please provide a estimated hours")
	@DecimalMin("1.00")
	@Column(name = "estimated_hours")
	private float estimatedHours;

	@Column(name = "is_deleted", columnDefinition = "BIT(1)")
	private boolean isDeleted;

	@Column(name = "route_complexity")
	private int routeComplexity;
	
	@NotEmpty(message = "Please provide a starting point")
	@Column(name = "starting_point")
	private String startingPoint;

	// bi-directional many-to-one association to Trip
	@JsonBackReference(value = "trip-route")
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "route")
	private List<Trip> trips;

	public Route() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getDestinationPoint() {
		return this.destinationPoint;
	}

	public void setDestinationPoint(String destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

	public float getDistance() {
		return this.distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getEstimatedHours() {
		return this.estimatedHours;
	}

	public void setEstimatedHours(float estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getRouteComplexity() {
		return this.routeComplexity;
	}

	public void setRouteComplexity(int routeComplexity) {
		this.routeComplexity = routeComplexity;
	}

	public String getStartingPoint() {
		return this.startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}

	public List<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Trip addTrip(Trip trip) {
		getTrips().add(trip);
		trip.setRoute(this);

		return trip;
	}

	public Trip removeTrip(Trip trip) {
		getTrips().remove(trip);
		trip.setRoute(null);

		return trip;
	}

}