package com.springbatch.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PersonalCat")
public class Personal 
{	
	@Id
	private int policy;
	
	private String expiry;
	
	private String location;
	
	private String state;
	
	private String region;
	
	private String insuredValue;
	
	private String category;
	
	private String name;
	
	private String pos;
	
	private String ht;
	
	private String wt;
	
	private String age;
	
	private String Exp;
	
	private String college;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;

	public int getPolicy() {
		return policy;
	}

	public void setPolicy(int policy) {
		this.policy = policy;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getInsuredValue() {
		return insuredValue;
	}

	public void setInsuredValue(String insuredValue) {
		this.insuredValue = insuredValue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getExp() {
		return Exp;
	}

	public void setExp(String exp) {
		Exp = exp;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHt() {
		return ht;
	}

	public void setHt(String ht) {
		this.ht = ht;
	}

	public String getWt() {
		return wt;
	}

	public void setWt(String wt) {
		this.wt = wt;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Personal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personal(int policy, String expiry, String location, String state, String region, String insuredValue,
			String category, String name, String pos, String ht, String wt, String age, String exp, String college,
			String firstName, String lastName, String email, String phone) {
		super();
		this.policy = policy;
		this.expiry = expiry;
		this.location = location;
		this.state = state;
		this.region = region;
		this.insuredValue = insuredValue;
		this.category = category;
		this.name = name;
		this.pos = pos;
		this.ht = ht;
		this.wt = wt;
		this.age = age;
		Exp = exp;
		this.college = college;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Insurance [policy=" + policy + ", expiry=" + expiry + ", location=" + location + ", state=" + state
				+ ", region=" + region + ", insuredValue=" + insuredValue + ", category=" + category + ", name=" + name
				+ ", pos=" + pos + ", ht=" + ht + ", wt=" + wt + ", age=" + age + ", Exp=" + Exp + ", college="
				+ college + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone="
				+ phone + "]";
	}

}
