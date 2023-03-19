package staffpublisher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StaffServiceImpl implements StaffService {
	
	private static final String FILE_NAME = "staff_data.txt";
	
	public StaffServiceImpl() {
		
	}

	
	    private List<Staff> staffList = new ArrayList<>();

	    @Override
	    public List<Staff> getAllStaff() {
	    	
	    	
	    	List<Staff> staffList = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int id = Integer.parseInt(parts[0]);
	                String name = parts[1];
	                String role = parts[2];
	                Staff staff = new Staff(id, name, role);
	                staffList.add(staff);
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading staff data from file: " + e.getMessage());
	        }
	        return staffList;
	    }
	     
	    @Override
	    public boolean getAvailability(int id) {
	    	
	    	try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int staffId = Integer.parseInt(parts[0]);
	                if (staffId == id) {
	                    return true;
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error getting staff data from file: " + e.getMessage());
	        }
	        return false;
	    	
	    }

	    @Override
	    public Staff getStaffById() {
	    	
	    	System.out.print("Enter staff ID: ");
	    	
	    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	        	
	        	
	        	int id =  Integer.parseInt(input.readLine()) ;
	        
	        
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int currentId = Integer.parseInt(parts[0]);
	                if (currentId == id) {
	                    String name = parts[1];
	                    String role = parts[2];
	                    return new Staff(id,name,role);
	                }
	            }
	        }catch (IOException e) {
	             System.err.println("Error Searching staff by id: " + e.getMessage());
	         }
	        System.out.println("No staff member found with that ID ");
	        return null;
	    }
	    
	    
	    @Override
	    public List<Staff> getStaffByName() {
	    	System.out.print("Enter Staff Name: ");
	    	
	    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	        
	        List<Staff> matchingStaff = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	        	
	        	String name =  input.readLine();
	        	
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int id = Integer.parseInt(parts[0]);
	                String staffName = parts[1];
	                String role = parts[2];
	                if (staffName.equalsIgnoreCase(name)) {
	                    matchingStaff.add(new Staff(id,staffName,role));
	                }
	            }
	        }catch (IOException e) {
	             System.err.println("Error Searching staff by Name: " + e.getMessage());
	         }
	        if (matchingStaff.isEmpty()) {
	            System.out.println("No staff found with that name ");
	        }
	        return matchingStaff;
	    }
	    

	    @Override
	    public void addStaffFromConsole() {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    	 try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
	                 ) {
	             System.out.print("Enter staff name: ");
	             String name = reader.readLine();
	             System.out.print("Enter staff role: ");
	             String role = reader.readLine();
	             int id = getNextId();
	             Staff staff = new Staff(id, name, role);
	             writer.write(staff.getId() + "," + staff.getName() + "," + staff.getRole() + "\n");
	             System.out.println("Staff added successfully.");
	             
//	             close
//	             writer.close();
	         } catch (IOException e) {
	             System.err.println("Error adding staff to file: " + e.getMessage());
	         }
	        
	    }

	    @Override
	    public void updateStaffFromConsole() {	    	
			
	    	try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
	    			BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME + ".tmp"))) {
	    		
	            boolean found = false;
	            System.out.print("Enter staff ID to update: ");
	            
	            
	            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	            int idToUpdate = Integer.parseInt(input.readLine()) ;
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int id = Integer.parseInt(parts[0]);
	                String name = parts[1];
	                String role = parts[2];
//	                writer.write(" Hello \n");
	                if (id == idToUpdate) {
	                    found = true;
	                    System.out.println(name+" "+role);
	                    System.out.print("Enter new staff name: ");
	                    String newName = new BufferedReader(new InputStreamReader(System.in)).readLine();
	                    System.out.print("Enter new staff role: ");
	                    String newRole = new BufferedReader(new InputStreamReader(System.in)).readLine();
	                    Staff staff = new Staff(id, newName, newRole);
	                    writer.write(staff.getId() + "," + staff.getName() + "," + staff.getRole() + "\n");
	                    System.out.println("Staff updated successfully.");
	                } else {
	                    writer.write(line + "\n");
	                }
	            }
	            if (!found) {
	                System.out.println("No staff found with ID " + idToUpdate);
	            }
	        } catch (IOException e) {
	            System.err.println("Error updating staff in file: " + e.getMessage());
	        }
	    	
	        // Replace original file with updated one
	        File file = new File(FILE_NAME);
	        file.delete();
	        File newfile = new File(FILE_NAME + ".tmp");
	        newfile.renameTo(file);
	    
	    }

	    @Override
	    public void deleteStaffFromConsole() {	    	
	    	
	    	 try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
	                 BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME + ".tmp"))) {
	             boolean found = false;
	             System.out.print("Enter staff ID to delete: ");
	             
	             
	             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	             int idToDelete = Integer.parseInt(input.readLine());
	             String line;
	             while ((line = reader.readLine()) != null) {
	             String[] parts = line.split(",");
	             int id = Integer.parseInt(parts[0]);
	             if (id == idToDelete) {
	             found = true;
	             System.out.println("Staff deleted successfully.");
	             } else {
	             writer.write(line + "\n");
	             }
	             }
	             if (!found) {
	             System.out.println("No staff found with ID " + idToDelete);
	             }
	             } catch (IOException e) {
	             System.err.println("Error deleting staff from file: " + e.getMessage());
	             }
	             // Replace original file with updated one
	             File file = new File(FILE_NAME);
	             file.delete();
	             File newfile = new File(FILE_NAME + ".tmp");
	             newfile.renameTo(file);
	    	
	    }
	
	    // to auto increment the staff id for every new staff member
	    private int getNextId() throws IOException {
	        int id = 1;
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                int currentId = Integer.parseInt(parts[0]);
	                if (currentId >= id) {
	                    id = currentId + 1;
	                }
	            }
	        }
	        return id;
	    }

}
