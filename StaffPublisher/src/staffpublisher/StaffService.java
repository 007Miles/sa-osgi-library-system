package staffpublisher;

import java.util.List;

public interface StaffService {
//	public void testdb();
	
	List<Staff> getAllStaff();
	boolean getAvailability(int id);
    Staff getStaffById();
    public List<Staff> getStaffByName();
    void addStaffFromConsole();
    void updateStaffFromConsole();
    void deleteStaffFromConsole();
	
}
