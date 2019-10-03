package s4.spring.td3.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private boolean suspended;
	@ManyToMany(mappedBy = "users")
	private Set<Group> groups;
	@ManyToOne
	private Organization organization;
	
	public User() {
		groups=new HashSet<>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isSuspended() {
		return suspended;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
