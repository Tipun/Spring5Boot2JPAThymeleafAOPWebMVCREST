package com.jrp.pma.entities;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jrp.pma.validators.UniqueValue;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_seq")
	@SequenceGenerator(name = "employee_seq", allocationSize = 1) 
	private long employeeId;
	
	@NotBlank(message="Must give a first name")
	@Size(min=2,max=50)
	private String firstName;
	
	@NotBlank(message="Must give a last name")
	@Size(min=1,max=50)
	private String lastName;
	
	@NotBlank
	@Email(message="Must give a valid email address")
	@UniqueValue
	//@Column(unique = true)
	private String email;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", 
	joinColumns=@JoinColumn(name="employee_id"),
	inverseJoinColumns=@JoinColumn(name="project_id")
			)
	@JsonIgnore
	private List<Project> projects;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
