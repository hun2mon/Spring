package kr.co.gudi.dto;

public class MemberDTO {

	private String id;
	private String pw;
	private String name;
	private int age;
	private String gender;
	private String email;
	
	
	public MemberDTO(String id, String name, int age, String email) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public MemberDTO(String id1,String pw ,String name1, int age1,String gender ,String email1) {
		this.id = id1;
		this.pw = pw;
		this.name = name1;
		this.age = age1;
		this.gender = gender;
		this.email = email1;
	}
	
	


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
