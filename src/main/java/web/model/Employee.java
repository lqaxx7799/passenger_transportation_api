package web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the employees database table.
 * 
 */
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

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "employee_name")
	private String employeeName;

	private String gender;

	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "is_working", columnDefinition = "BIT(1)")
	private boolean isWorking;

	@Column(name = "license_code")
	private String licenseCode;

	@Column(name = "license_type")
	private String licenseType;

	private int seniority;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "started_time")
	private Date startedTime;

	// bi-directional many-to-one association to Trip
	@OneToMany(mappedBy = "employee1")
	private List<Trip> trips1;

	// bi-directional many-to-one association to Trip
	@OneToMany(mappedBy = "employee2")
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