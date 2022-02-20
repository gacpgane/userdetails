package com.ing.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author prabuddha
 *
 */
@Entity
@Table(name = "ING_USER_ADDRESS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public class Address extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="POST_CODE")
	private Integer postcode;
 
}
