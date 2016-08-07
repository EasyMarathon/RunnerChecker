package com.easymarathon.bean;

public class Athlete {
	private String name = "";
	private boolean gender;
	private String iDcard = "";
	private String phone = "";
	private String urgencyName = "";
	private String urgencyPhone = "";
	private Integer athleteID=null;
	private String  address="";
	private String blood="";
	private String identityPic="";

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIDcard() {
		return iDcard;
	}
	public void setIDcard(String iDcard) {
		this.iDcard = iDcard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUrgencyName() {
		return urgencyName;
	}
	public void setUrgencyName(String urgencyName) {
		this.urgencyName = urgencyName;
	}
	public String getUrgencyPhone() {
		return urgencyPhone;
	}
	public void setUrgencyPhone(String urgencyPhone) {
		this.urgencyPhone = urgencyPhone;
	}

	public Integer getAthleteID() {
		return athleteID;
	}

	public void setAthleteID(Integer athleteID) {
		this.athleteID = athleteID;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public String getiDcard() {
		return iDcard;
	}
	public void setiDcard(String iDcard) {
		this.iDcard = iDcard;
	}
	public String getIdentityPic() {
		return identityPic;
	}
	public void setIdentityPic(String identityPic) {
		this.identityPic = identityPic;
	}
}
