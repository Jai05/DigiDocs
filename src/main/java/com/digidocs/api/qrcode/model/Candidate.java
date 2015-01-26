package com.digidocs.api.qrcode.model;

public class Candidate {

	private String fname = null;

	private String lname = null;

	private int age = 0;

	private String address_perm = null;

	private String mobile_num = null;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress_perm() {
		return address_perm;
	}

	public void setAddress_perm(String address_perm) {
		this.address_perm = address_perm;
	}

	public String getMobile_num() {
		return mobile_num;
	}

	public void setMobile_num(String mobile_num) {
		this.mobile_num = mobile_num;
	}

	@Override
	public String toString() {
		return new StringBuffer(" First Name : ").append(this.fname)
				.append(", Last Name : ").append(this.lname)
				.append(", Age : ").append(this.age).append(", Permanent Address : ")
				.append(this.address_perm).append(", Mobile Number : "+this.mobile_num).toString();
	}
}
