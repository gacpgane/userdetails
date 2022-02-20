/**
 * 
 */
package com.ing.usermanagement.model;

import javax.persistence.*;

import lombok.Data; 

/**
 * @author prabuddha
 *
 */
@Entity
@Table(name = "ING_USER")
@Data
public class User extends BaseModel{

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
	private long employeeId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ING_USER_ADDRESS_ID", referencedColumnName = "ID")
	private Address address;
	
}
