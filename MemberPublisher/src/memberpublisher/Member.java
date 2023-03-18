package memberpublisher;

public class Member {
	private int memberId;
	private String firstName;
	private String lastName;
	private String nic;
	private String dob;
	private String email;
	private String phoneNumber;
	private String address;
	private int staffid;
	
	public Member() {}
	
	public Member(int memberId, String firstName, String lastName, String nic, String dob, String email, String phoneNumber,String address,int staffid) {
		
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.nic = nic;
		this.staffid = staffid;

	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public int getStaffId() {
		return staffid;
	}

	public void setStaffId(int staffid) {
		this.staffid = staffid;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}
	
	public String toString() {
        return "Member [memberid=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + "+, dob=" + dob + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address +", nic=" + nic +", staffid=" + staffid +"]";
    }
	

}
