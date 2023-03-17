package staffmanagement;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import staffpublisher.Staff;
import staffpublisher.StaffService;

public class Activator implements BundleActivator {

	 
	ServiceReference staffServiceReference;
	private Scanner scanner;
//	private static BundleContext context;
//
//	static BundleContext getContext() {
//		return context;
//	}

	public void start(BundleContext bundleContext) throws Exception {
//		Activator.context = bundleContext;
		
		staffServiceReference = bundleContext.getServiceReference(StaffService.class.getName());
		scanner = new Scanner(System.in);
		
		
		@SuppressWarnings("unchecked")
		StaffService staffService = (StaffService) bundleContext.getService(staffServiceReference);
		
        while (true) {
            System.out.println("-----------------   Library Staff Management System   ---------------------");
            System.out.println("1. List all staff members");
            System.out.println("2. Add a new staff member");
            System.out.println("3. Update staff member details");
            System.out.println("4. Delete a staff member");
            System.out.println("5. Search Staff member");
            System.out.println("6. Exit");
            System.out.println("---------------------------------------------------------------------------");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1	:	
                	List<Staff> staffList = staffService.getAllStaff();
                	for (Staff staff : staffList) {
                	System.out.println(staff);
                	}
                	break;
                case 2:
                    staffService.addStaffFromConsole();
                    break;

                case 3:
                    staffService.updateStaffFromConsole();
                    break;

                case 4:
                    staffService.deleteStaffFromConsole();
                    break;
                    
                case 5:
                	System.out.println("---------------------------------------------------------------------------");
                	System.out.println("1. Search by ID");
                    System.out.println("2. Search by Name");
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.print("Enter choice: ");

                    int key = scanner.nextInt();
                    scanner.nextLine();
                	
                	switch (key) {
					case 1:
//						System.out.println(staffService.getAvailability(2));
						Staff staff = staffService.getStaffById();
	                	if(staff != null) {
	                		System.out.println(staff);
	                	}
						break;
					case 2:
						List<Staff> searchResult = staffService.getStaffByName();
						for (Staff s : searchResult) {
		                	System.out.println(s);
		                	}
	                	break;
						

					default:
	                    System.out.println("Invalid choice. Please try again.");
	                    System.out.println("---------------------------------------------------------------------------");
						break;
					}
                	
                	break;

                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("---------------------------------------------------------------------------");
                    break;
            }

            System.out.println();
        }
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Staff Management service stopped !!!");
		bundleContext.ungetService(staffServiceReference);
	}

}
