package memberpublisher;

import java.util.List;

public interface MemberService {
	
	public void insertMemberDetails();
	public void searchMemberDetails(String keyword);
	public void deleteMember(String dnic);
	public List<Member> getMemberAll();
	public void editMemberDetails(String nic); 
	public boolean getAvailabilityByID(int memberid);
	
	
}
