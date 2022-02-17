/**
 * 
 */
package com.ing.usermanagement.model;

import javax.persistence.*; 

/**
 * @author prabuddha
 *
 */
@Entity
@Table(name = "ING_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="EMPLOYEE_ID")
	private Long employeeId;
	
	 
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ING_USER_ADDRESS_ID", referencedColumnName = "ID")
	private Address address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
