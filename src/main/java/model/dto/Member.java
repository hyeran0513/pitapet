package model.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Member implements Serializable{
	private String id;
	private String password;
	private String name;
	private String birth;
	private String gender;
	private String phone;
	private String address;
	private String email;
	private String identity;
	private List<Pet> myPets;
	private String profileImage;
	
	public Member() {
		super();
	}
	
	public Member(String id) {
		super();
		this.id = id;
	}
	
	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Member(String id, String address, String profileImage) {
		super();
		this.id = id;
		this.address = address;
		this.profileImage = profileImage;
	}
	
	public Member(String id, String email, String phone, String address) {
		super();
		this.id = id;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}
	
	public Member(String id, String password, String email, String phone, String address) {
		super();
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}
	
	public Member(String id, String password, String name, String birth, String gender, String email, String phone, String address, String identity, String profileImage) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.identity = identity;
		this.profileImage = profileImage;
	}
	
	public Member(String id, String name, String gender, String phone, String address, String profileImage) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.profileImage = profileImage;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public List<Pet> getMyPets() {
		return myPets;
	}
	public void setMyPets(List<Pet> myPets) {
		this.myPets = myPets;
	}	
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}	
	public boolean matchPassword(String password) {
		if(password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	public boolean isSameMember(Member member) {
		return id.equals(member.id);
	}
}
