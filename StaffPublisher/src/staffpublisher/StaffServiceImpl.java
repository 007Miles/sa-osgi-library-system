package staffpublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lms_db.DatabaseImpl;
import lms_db.Database;

public class StaffServiceImpl implements StaffService {
	
	Connection connection;
	Database database;
	Scanner scan;
	
	public StaffServiceImpl() {
		database = new DatabaseImpl();
		connection = database.getDatabaseConnection();
		scan = new Scanner(System.in);
		
	}

	
	    private List<Staff> staffList = new ArrayList<>();

	    @Override
	    public List<Staff> getAllStaff() {
	        return staffList;
	    }

	    @Override
	    public Staff getStaffById(int id) {
	        for (Staff staff : staffList) {
	            if (staff.getId() == id) {
	                return staff;
	            }
	        }
	        return null;
	    }

	    @Override
	    public void addStaffFromConsole() {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter staff ID: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();

	        System.out.print("Enter staff name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter staff role: ");
	        String role = scanner.nextLine();

	        Staff staff = new Staff(id, name, role);
	        staffList.add(staff);
	        System.out.println("Staff added successfully.");
	    }

	    @Override
	    public void updateStaffFromConsole() {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter staff ID to update: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();

	        Staff staff = getStaffById(id);
	        if (staff == null) {
	            System.out.println("Staff with ID " + id + " not found.");
	            return;
	        }

	        System.out.print("Enter new name for staff: ");
	        String name = scanner.nextLine();
	        staff.setName(name);

	        System.out.print("Enter new role for staff: ");
	        String role = scanner.nextLine();
	        staff.setRole(role);

	        System.out.println("Staff updated successfully.");
	    }

	    @Override
	    public void deleteStaffFromConsole() {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter staff ID to delete: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();

	        Staff staff = getStaffById(id);
	        if (staff == null) {
	            System.out.println("Staff with ID " + id + " not found.");
	            return;
	        }

	        staffList.remove(staff);
	        System.out.println("Staff deleted successfully.");
	    }
	

	
	
//	@Override
//	public void testdb() {
//		
//		String query = "SELECT * " + 
//				"FROM user " +
//				"WHERE username=?;";
//		try {
//		PreparedStatement preparedstatement = connection.prepareStatement(query);
//		preparedstatement.setString(1, "uname1");
//		ResultSet rs = preparedstatement.executeQuery();
//		
//		System.out.println(rs.getString(1));
//		System.out.println("test");
//		
//		}catch (SQLException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//
//	}

}
