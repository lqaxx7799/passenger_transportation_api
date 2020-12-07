package web.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id")
	private int id;

	@NotEmpty(message = "Please provide address")
	private String address;

	@NotNull(message = "Please provide date of birth, format: yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@NotEmpty(message = "Please provide employee name")
	@Column(name = "employee_name")
	private String employeeName;

	@NotEmpty(message = "Please provide gender")
	private String gender;

	@NotEmpty(message = "Please provide identification number")
	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "is_working", columnDefinition = "BIT(1)")
	private boolean isWorking;

	@NotEmpty(message = "Please provide license code")
	@Column(name = "license_code")
	private String licenseCode;

	@NotEmpty(message = "Please provide license type")
	@Column(name = "license_type")
	private String licenseType;

	@NotNull(message = "Please provide seniortity, must be a number")
	@Range(min = 0, max = 100)
	private int seniority;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "started_time")
	private Date startedTime;

	// bi-directional many-to-one association to Trip
	@JsonBackReference(value = "trip-driver")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee1")
	private List<Trip> trips1;

	// bi-directional many-to-one association to Trip
	@JsonBackReference(value = "trip-assistant")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee2")
	private List<Trip> trips2;

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdentificationNumber() {
		return this.identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public boolean getIsWorking() {
		return this.isWorking;
	}

	public void setIsWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	public String getLicenseCode() {
		return this.licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getLicenseType() {
		return this.licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public int getSeniority() {
		return this.seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public Date getStartedTime() {
		return this.startedTime;
	}

	public void setStartedTime(Date startedTime) {
		this.startedTime = startedTime;
	}

	public List<Trip> getTrips1() {
		return this.trips1;
	}

	public void setTrips1(List<Trip> trips1) {
		this.trips1 = trips1;
	}

	public Trip addTrips1(Trip trips1) {
		getTrips1().add(trips1);
		trips1.setEmployee1(this);

		return trips1;
	}

	public Trip removeTrips1(Trip trips1) {
		getTrips1().remove(trips1);
		trips1.setEmployee1(null);

		return trips1;
	}

	public List<Trip> getTrips2() {
		return this.trips2;
	}

	public void setTrips2(List<Trip> trips2) {
		this.trips2 = trips2;
	}

	public Trip addTrips2(Trip trips2) {
		getTrips2().add(trips2);
		trips2.setEmployee2(this);

		return trips2;
	}

	public Trip removeTrips2(Trip trips2) {
		getTrips2().remove(trips2);
		trips2.setEmployee2(null);

		return trips2;
	}

}