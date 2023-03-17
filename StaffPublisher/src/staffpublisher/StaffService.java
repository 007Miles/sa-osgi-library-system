package staffpublisher;

import java.util.List;

public interface StaffService {
//	public void testdb();
	
	List<Staff> getAllStaff();
    Staff getStaffById(int id);
    void addStaffFromConsole();
    void updateStaffFromConsole();
    void deleteStaffFromConsole();
	
}
