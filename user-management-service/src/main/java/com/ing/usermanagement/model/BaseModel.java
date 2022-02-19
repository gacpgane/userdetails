package com.ing.usermanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

/**
 * 
 * @author prabuddha
 *
 */
@MappedSuperclass
public class BaseModel {

	private static final long serialVersionID =1L;
	
	@Column(name="CREATE_TS",nullable=false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name="CHANGE_TS",nullable=false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	
}
