package memberpublisher;

import java.util.List;




public interface MemberService {
	public void insertMemberDetails();
	

	Member getMemberById(int memberid);
//	List<Member> getAllMemberDetails();
	public void searchMemberDetails(String keyword);
//	public void updateMemberDetails();
	public void deleteMember(String dnic);
	
	public List<Member> getMemberAll();
	public void editMemberDetails(String nic); 
	public boolean getAvailabilityByID(int memberid);
	
}
